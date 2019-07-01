import React, { Component } from "react"
import { Route, Redirect, Switch, withRouter } from "react-router-dom"
import {Button, Card} from "react-bootstrap"
import Navigation from 'components/Navigation'
import Place from 'components/place'
import axios from "axios";

class AdminPlaces extends Component {
	constructor(props) {
		super(props)
		this.state = {
			allPlaces : [],
		}
	}

	componentDidMount() {
		const token = localStorage.getItem("token")
		axios.get(API+'/places', {
			headers: {
				Authorization: 'Bearer ' + token
			}
		})
		.then(response => {
			this.setState({
				allPlaces: response.data.body
			})
		}).catch(error => {
			console.log(error.response.data.message)
		})
	}

	render() {
		const allPlaces = this.state.allPlaces.map(place => {
			return(
				<div>
					<Place key={place.foursquareId} id={place.foursquareId} title={place.name}
					       subTitle={place.address}
					       description={"Some Desc"}
					       showAddButon={false}
					       userName={this.props.userName}
					       showUsersAdded={true}
					       usersInterested={place.usersWhoMarkedAsFavourite}
					/>
				</div>
			)
		})
		return (
			<div>
				<Navigation title= "Home" history={this.props.history}/>
				<h2>All places registered</h2>
				{allPlaces}
			</div>
		)
	}
}

export default withRouter(AdminPlaces)