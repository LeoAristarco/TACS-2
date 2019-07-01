import React, { Component } from "react"
import Navigation from 'components/Navigation'
import PlaceCard from 'components/PlaceCard'
import { geolocated } from 'react-geolocated'
import axios from 'axios'

class PlaceView extends Component {
	constructor(props) {
		super(props)
		this.state = {
			places : []
		}
		this.initDashboard = this.initDashboard.bind(this)
	}

	initDashboard() {
		const location = this.props.coords.latitude + ',' + this.props.coords.longitude
		const token = localStorage.getItem("token")
		try {
			axios.get(API +'/places/near?coordinates=' + location, {
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

    componentDidUpdate(prevProps) {
        if (this.props.coords && !prevProps.coords) {
	        this.initDashboard()
        }
    }

	render() {
		const places = this.state.places.map(place => {
			return(
				<PlaceCard key={place.id} cardName = {place.name} id = {place.id} url = {place.location.address}
				           image = "w3-grayscale-max" userName={this.props.userName} />
			)
		})
		return (
			<div className="main-container">
				<Navigation title= "Home" history={this.props.history} />
				<div className= "container">
					<div className= "row mt-4">
						<div className="row active-with-click">
							{places}
						</div>
					</div>
				</div>
			</div>
		)
	}
}

export default geolocated({
	positionOptions: {
		enableHighAccuracy: false,
	},
	userDecisionTimeout: 5000,
})(PlaceView)
