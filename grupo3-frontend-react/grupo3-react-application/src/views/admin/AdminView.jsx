import React, { Component } from "react"
import axios from 'axios'
import Navigation from 'components/Navigation'
import PlaceCard from 'components/PlaceCard'

class AdminView extends Component {
	constructor() {
		super()
		this.state = {
			places: [
				{ "id": "1",
					"name": "Washington Square Park",
					"location": "https://i.pinimg.com/564x/d7/be/ea/d7beea08f312788090240120bd929b01.jpg"
				}
				,
				{ "id": "2",
					"name": "South Park",
					"location": "https://i.pinimg.com/564x/7a/6b/e3/7a6be38270ff81198292148236222748.jpg"
				}
				,
				{ "id": "3",
					"name": "Washington DC",
					"location": "https://i.pinimg.com/564x/55/9e/43/559e43ef4ff55bf74be9c21e35191234.jpg"
				},
				{ "id": "4",
					"name": "La Noria",
					"location": "https://i.pinimg.com/564x/ed/59/7e/ed597ee6b1bba5a37a4ec337b26ce737.jpg"
				}
				,
				{ "id": "5",
					"name": "Mexico",
					"location": "https://i.pinimg.com/564x/13/42/36/134236aa9b5b7a6e5e400d8f5fbb34e4.jpg"
				}
				,
				{ "id": "6",
					"name": "China",
					"location": "https://i.pinimg.com/564x/f1/1e/82/f11e82851e2390f633d2ceb15a51b021.jpg"
				}
			],
			placesRegistered : 0,
		}
	}
	getPlacesRegistered(days) {
		axios.get(API+'/administrator/places/registered-places?days=' + days, {
			headers: {
				Authorization: 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKUGVyZXoxIiwiUk9MIjoiQURNSU4ifQ.3I3laL6doeyt7iSf_Ew_dHAKqIHSeHFE5SmfwwkDh0Gx-1qhBY9GmT11iz8KS3_dEvOlBzn8uM2t4f2ZGxHYbQ'
			}
		})
		.then(response =>
			this.setState({placesRegistered : response.data.totalRegisteredPlaces})
		)
		.catch(err => {
			console.log(err)
		})
	}
	render() {
		const places = this.state.places.map(place => {
			return(
				<PlaceCard key={place.id} cardName = {place.name} id = {place.id} url = {place.location.address} image="thumb"/>
			)
		})
		return (
			<div className="main-container">
				<Navigation title= "Home" />

				<div className= "helper">
					<div className= "row mt-4">
						<p><strong>Borrar todo esto.</strong></p>
						<p>View solo para admins.</p>
						<p>Filtrar places por registrationDate.</p>
						<p>Ver si hace falta mostrar los places o solo poner numeros y estadisticas.</p>
						<p>Ver que atributos mostrar de cada place si hiciera falta mostrarlos.</p>
						<p>Sacar el active del boton "all".</p>
					</div>
				</div>

				<div className= "container">
					<div className= "row mt-4">
						<div className="btn-group" role="group">
                            <span className="input-group-btn">
                                <b>See registered places:</b>
                                <button type="button" className="btn btn-primary" onClick={() => this.getPlacesRegistered(0)}>today</button>
	                            <button type="button" className="btn btn-primary" onClick={() => this.getPlacesRegistered(3)}>last 3 days</button>
								<button type="button" className="btn btn-primary" onClick={() => this.getPlacesRegistered(7)}>last week</button>
								<button type="button" className="btn btn-primary" onClick={() => this.getPlacesRegistered(30)}>last month</button>
								<button type="button" className="btn btn-primary" onClick={() => this.getPlacesRegistered(-1)}>all</button>
                            </span>
							<div><b>Amount places registered: {this.state.placesRegistered}</b></div>
						</div>

						<div className="row active-with-click">
							{places}
						</div>
					</div>
				</div>
			</div>
		)
	}
}

export default AdminView
