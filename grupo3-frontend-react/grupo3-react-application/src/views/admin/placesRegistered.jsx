import React, { Component } from "react"
import { Route, Redirect, Switch, withRouter } from "react-router-dom"
import {Button, ButtonToolbar} from "react-bootstrap"
import axios from "axios";
import Navigation from 'components/Navigation'

class PlacesRegistered extends Component {
	constructor(props) {
		super(props)
		this.state = {
			placesRegistered : 0,
		}
		this.getPlacesRegistered = this.getPlacesRegistered.bind(this)
	}

	getPlacesRegistered(days) {
		const token = localStorage.getItem("token")
		axios.get(API+'/administrator/places/registered-places?days=' + days, {
			headers: {
				Authorization: 'Bearer '+ token
			}
		})
		.then(response => {
			this.setState({placesRegistered: response.data.body.length})
		})
		.catch(err => {
			alert(err.response.data.message)
		})
	}

	render() {
		return (
			<div>
				<Navigation title= "Home" history={this.props.history}/>
				<h1>Amount Places Registered</h1><br />
				<h1>{this.state.placesRegistered}</h1>
				<ButtonToolbar>
					<Button variant="primary" size="lg" onClick={() => this.getPlacesRegistered(0)}>today</Button>
					<Button variant="primary" size="lg" onClick={() => this.getPlacesRegistered(3)}>last 3 days</Button>
					<Button variant="primary" size="lg" onClick={() => this.getPlacesRegistered(7)}>last week</Button>
					<Button variant="primary" size="lg" onClick={() => this.getPlacesRegistered(30)}>last month</Button>
					<Button variant="primary" size="lg" onClick={() => this.getPlacesRegistered(-1)}>all</Button>
				</ButtonToolbar>
			</div>
		)
	}
}

export default withRouter(PlacesRegistered)