package utn.tacs.grupo3.spring.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utn.tacs.grupo3.model.User;
import utn.tacs.grupo3.model.exception.signUpValidation.ExceptionByEmptyFields;
import utn.tacs.grupo3.model.exception.signUpValidation.ExceptionbyRepeatedUserName;
import utn.tacs.grupo3.model.exception.signUpValidation.ExceptionbySignUpValidation;
import utn.tacs.grupo3.model.exception.signUpValidation.ExceptionbyVeryShortPassword;
import utn.tacs.grupo3.model.exception.signUpValidation.ExceptionbyVeryshortUserName;
import utn.tacs.grupo3.repository.mongo.UserRepository;
import utn.tacs.grupo3.spring.configuration.SpringControllerTestConfiguration;
import utn.tacs.grupo3.spring.validations.SignUpValidation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringControllerTestConfiguration.class)
public class SignUpValidationTest {

    private User user1;
    private SignUpValidation signUpValidation;
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoOperations mongoOps;

    @Before
    public void initialize() {
    	if (!mongoOps.collectionExists(User.class)) {
			mongoOps.createCollection(User.class);
		}

        user1 = new User("Pedro", "Rodriguez", "pedrito", "", null);
        signUpValidation = new SignUpValidation();
    }

    @After
    public void tearDown() {
    	if (mongoOps.collectionExists(User.class)) {
			mongoOps.dropCollection(User.class);
		}
    }


    @Test(expected = ExceptionbyVeryshortUserName.class)
    public void userNameMustBeGreaterThan2Characters() throws ExceptionbySignUpValidation {
        user1.setUsername("AA");
        signUpValidation.validate(user1, userRepository);
    }

    @Test(expected = ExceptionbyVeryShortPassword.class)
    public void passwordMustBeGreaterThan2Characters() throws ExceptionbySignUpValidation {
        user1.setPassword("12");
        signUpValidation.validate(user1, userRepository);
    }

    @Test(expected = ExceptionbyRepeatedUserName.class)
    public void usernameAlreadyExists() throws ExceptionbySignUpValidation {
        userRepository.save(user1 = new User("Pedro", "Rodriguez", "pedrito", "", null));
        signUpValidation.validate(user1, userRepository);
    }

    @Test(expected = ExceptionByEmptyFields.class)
    public void theFirstNameIsEmpty() throws ExceptionbySignUpValidation {
        userRepository.save(user1 = new User(null, "Rodriguez", "", "", null));
        signUpValidation.validate(user1, userRepository);
    }

    @Test(expected = ExceptionByEmptyFields.class)
    public void theLastNameAndUserNameIsEmpty() throws ExceptionbySignUpValidation {
        userRepository.save(user1 = new User("Pedro", null, null, "", null));
        signUpValidation.validate(user1, userRepository);
    }

}
