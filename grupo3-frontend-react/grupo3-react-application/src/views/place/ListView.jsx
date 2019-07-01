import React, { Component } from "react"
import axios from 'axios'
import Navigation from 'components/Navigation'
import ListCard from 'components/ListCard'

class ListView extends Component {
	constructor(props) {
		super(props)
		this.state = {
			lists : []
		}
		this.updateListOfPlaces = this.updateListOfPlaces.bind(this)
	}

	listFromUser() {
		const token = localStorage.getItem("token")
		try {
			axios.get(API+'/users/'+ this.props.userName +'/list-of-places', {
				headers: {
					Authorization: 'Bearer ' + token
				}
			})
			.then(response => {
				this.setState({lists: response.data.body })
			}).catch(error => {
				alert(error.response.data.message)
			})
		} catch (e) {
			console.log(e)
		}
	}

	componentDidMount() {
		if (this.props.userName) {
			this.listFromUser()
		}
	}

	updateListOfPlaces(){
		this.listFromUser()
	}

	render() {
		const lists = this.state.lists.map((list,i) => {
			return(
				<ListCard key={i} cardName = {list.listName} id = {list.id} url = {list.icon}
						  history={this.props.history}
				          palces={list.places}
				          userName={this.props.userName}
				          updateListOfPlaces={this.updateListOfPlaces}
				/>
			)
		})

		return (
			<div className="main-container">
				<Navigation title= "Home" history={this.props.history}/>
				<div className= "container md-2">
					<h3>My lists</h3>
					<div className= "container">
						<div className= "row">
							<div className="row active-with-click">
								{lists}
							</div>
						</div>
					</div>
				</div>
			</div>
		)
	}
}

export default ListView
