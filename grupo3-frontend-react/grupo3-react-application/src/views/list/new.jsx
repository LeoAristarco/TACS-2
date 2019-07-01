import React, { Component } from "react"
import { Form, Button, ButtonToolbar, FormControl, Alert } from "react-bootstrap"
import CSSModules from "react-css-modules"
import axios from "axios"
import Search from "components/search"
import Place from "components/place"

import styles from "./new.css"
import {geolocated} from "react-geolocated";

class ListNew extends Component {
	constructor(props){
		super(props)
		this.state = {
			listName: '',
			canAddPlacesToList : false,
			places: [],
		}
		this.searchNearPlaces = this.searchNearPlaces.bind(this)
		this.addPlaceToList = this.addPlaceToList.bind(this)
	//	this.removePlaceToList = this.removePlaceToList.bind(this)
	}

	handleChange = event => {
		this.setState({
			[event.target.id] : event.target.value
		})
	}

	handleSubmit = async event => {
		event.preventDefault()
		const token = localStorage.getItem("token")
		try {
			axios.post(API+'/users/'+ this.props.userName +'/list-of-places/'+ this.state.listName,{}, {
				headers: {
					Authorization: 'Bearer ' + token
				}
			})
			.then(res => {
				alert(res.data.message)
				this.setState({
					canAddPlacesToList : true
				})
			})
		} catch (error) {
			alert(error)
		}
	}

	addPlaceToList(place) {
		event.preventDefault()
		const token = localStorage.getItem("token")
		try {
			axios.post(API+'/users/'+ this.props.userName +'/list-of-places/'+ this.state.listName +'/'+ place.id,
				{}, {
				headers: {
					Authorization: 'Bearer ' + token
				}
			})
			.then(res => {
				console.log(res)
				alert(res.data.message)
				this.props.history.push('/home')
				// this.setState(state => {
				// 	const listPlaces = [...state.places, place]
				// 	return {
				// 		places : listPlaces
				// 	}
				// })
			})
		} catch (error) {
			alert(error)
		}
	}

	searchNearPlaces(){
		const location = this.props.coords.latitude + ',' + this.props.coords.longitude
		const token = localStorage.getItem("token")
		try {
			axios.get(API+'/places/near?coordinates=' + location, {
				headers: {
					Authorization: 'Bearer ' + token
				}
			})
			.then(response => {
				this.setState({places : response.data.body})
			})
		} catch (e) {
			console.log(e)
		}
	}

	// removePlaceToList(placeId) {
	// 	this.setState( state => {
	// 		const places = state.places.filter(place => place.id !== placeId)
	// 		return {
	// 			places
	// 		}
	// 	})
	// }

	render() {
		const places = this.state.places.map(place => {
			return(
				<Place key={place.id} id={place.id} title={place.name}
				       subTitle={place.location.address}
				       description={"Some Desc"}
				       addPlaceToList={this.addPlaceToList}
				       showAddButon={true}
				       showVisitedButton={false}
				       showUsersAdded={false}
				/>
			)
		})
		return (
			<div>
				{!this.state.canAddPlacesToList ? (
				<Form onSubmit={this.handleSubmit}>
					<h2>Select List Name</h2>
					<Form.Group controlId="listName">
						<Form.Control
							autoFocus
							value={this.state.listName}
							onChange={this.handleChange}
							placeholder="Put List name"
						/>
					</Form.Group>
					<Button	type="submit" variant="primary">Create List</Button>
				</Form>) : (
					<div>
						<h3>Search Places</h3>
						<Search/>
						<ButtonToolbar className="button-container">
							<Button variant="primary" onClick={this.searchNearPlaces}>Near</Button>
						</ButtonToolbar>
						<div className="places-container">
							{places}
						</div>
					</div>
				)}
			</div>
		)
	}
}

export default geolocated({
	positionOptions: {
		enableHighAccuracy: false,
	},
	userDecisionTimeout: 5000,
})(ListNew)
