import React, { Component } from "react"
import { withRouter } from "react-router-dom"
import {Button, Card} from "react-bootstrap"
import axios from "axios/index";
import Place from 'components/place'
import Navigation from 'components/Navigation'


class PlacesList extends Component {
	constructor(props) {
		super(props)
		this.state = {
			placesInList : [],
			isVisited: false,
		}
		this.getPlacesInList = this.getPlacesInList.bind(this)
	}

	componentDidMount() {
		this.getPlacesInList()
	}

	getPlacesInList() {
		const token = localStorage.getItem("token")
		axios.get(API+'/users/'+ this.props.userName +'/list-of-places', {
			headers: {
				Authorization: 'Bearer ' + token
			}
		})
		.then(response => {
			let places = response.data.body.filter(entity => entity.listName === this.props.match.params.id)
			this.setState({
				placesInList : places[0].places || []
			})
		}).catch(error => {
			console.log(error)
		})
	}

	render() {
		const places = this.state.placesInList.map(place => {
			return(
				<div>
					<Place key={place.foursquareId} id={place.foursquareId} title={place.name}
					       subTitle={place.address}
					       description={"Some Desc"}
					       showAddButon={false}
					       showVisitedButton={true}
					       userName={this.props.userName}
					       showUsersAdded={false}
					/>
				</div>
			)
		})
		return (
			<div>
				<Navigation title= "Home" history={this.props.history}/>
				<h2>Places in List : {this.props.match.params.id}</h2>
				{places}
			</div>
		)
	}
}

export default withRouter(PlacesList)
