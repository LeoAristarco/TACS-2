import React, { Component } from "react"
import { Route, Redirect, Switch, withRouter } from "react-router-dom"
import LogInUser from "views/login/user/login_user"
import SignUp from "views/signup/user/signup_user"
import PlaceView from "views/place/PlaceView"
import ListView from "views/place/ListView"
import AdminView from "views/admin/AdminView"
import UserView from "views/admin/UserView"
import Home from 'views/home/home'
import ListNew from 'views/list/new'
import PlacesList from 'views/list/places'
import AllPlaces from 'views/admin/adminPlaces'
import PlacesRegistered from 'views/admin/placesRegistered'
import axios from "axios";

class App extends Component {
	constructor(props) {
		super(props)
		this.state = {
			userLogged : false,
			token: '',
			user : {
				firstName : '',
				lastName : '',
				userName : '',
				password : '',
				listOfPlaces : [],
				role: '',
			},
		}
		this.updateProps = this.updateProps.bind(this)
		this.updateState = this.updateState.bind(this)
	}

	updateProps(key, value) {
		this.setState({
			user:{
				...this.state.user,
				[key]:value
			}
		})
	}

	updateState(key, value) {
		this.setState({
			[key]:value
		})
	}

	componentDidMount() {
		const token = localStorage.getItem("token")
		if(!token){
			this.props.history.push('/login')
		}
	}

	render() {
		if (!this.state.userLogged) {
			<Redirect to="/login" />
		}
		return (
			<div>
				<Switch>
					<Route path={"/login"}
					       component={() =>
								<LogInUser updateProps={this.updateProps}
								           updateState={this.updateState}
								           history={this.props.history} />
					       }
					/>
					<Route path={"/home"}
					       component={() =>
						       <Home updateState={this.updateState}
					                 history={this.props.history} />
					       }
					/>
					<Route path={"/signup"}
					       component={() =>
							<SignUp updateProps={this.updateProps}
									history={this.props.history}/>}
					/>
					<Route path={"/places"}
					       component={() =>
					       <PlaceView token={this.state.token}
					                  userName={this.state.user.userName}
					                  history={this.props.history} />}
					/>
					<Route path={"/list/new"}
					       component={() =>
						       <ListNew token={this.state.token}
						                userName={this.state.user.userName}
						                history={this.props.history}
						                />}
					/>
					<Route path={"/users/list-of-places"}
					       component={() =>
						       <ListView token={this.state.token}
						                 userName={this.state.user.userName}
						                 history={this.props.history}
						       />}
					/>
					<Route path={"/list/places/:id"}
					       component={() =>
						       <PlacesList userName={this.state.user.userName}
						                 history={this.props.history}
						       />}
					/>
					<Route path={"/users"} component={UserView} />
					<Route path={"/admin/places"}
					       component={() =>
						       <AllPlaces userName={this.state.user.userName}
						                  history={this.props.history}
						       />}
					/>
					<Route path={"/admin/places-registered"}
					       component={() =>
						       <PlacesRegistered userName={this.state.user.userName}
						                         history={this.props.history}
						       />}
					/>
					<Route path={"/stats"} component={AdminView} />
				</Switch>
			</div>
		)
	}
}

export default withRouter(App)
