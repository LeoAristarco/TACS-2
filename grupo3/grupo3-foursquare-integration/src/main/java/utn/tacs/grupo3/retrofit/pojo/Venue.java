package utn.tacs.grupo3.retrofit.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Venue {
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("categories")
        @Expose
        public List<Category> categories = null;

        @SerializedName("referralId")
        @Expose
        public String referralId;
        @SerializedName("hasPerk")
        @Expose
        public Boolean hasPerk;

        public String getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public List<Category> getCategories() {
                return categories;
        }

        public String getReferralId() {
                return referralId;
        }

        public Boolean getHasPerk() {
                return hasPerk;
        }
}
