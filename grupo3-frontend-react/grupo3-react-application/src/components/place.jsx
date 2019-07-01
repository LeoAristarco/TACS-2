import React, { Component } from "react"
import { Route, Redirect, Switch, withRouter } from "react-router-dom"
import {Button, Card} from "react-bootstrap"
import axios from "axios";

class Place extends Component {
	constructor(props) {
		super(props)
		this.state = {
			place:{
				title: '',
				subTitle: '',
				description: '',
			},
			isVisited : false,
			showUsersInterested : false,
		}
		this.addPlace = this.addPlace.bind(this)
		this.markAsVisitedPlace = this.markAsVisitedPlace.bind(this)
		this.showUsersInterested =this.showUsersInterested.bind(this)
	}

	addPlace() {
		const place = {
			id : this.props.id,
			title : this.props.title,
			subTitle : this.props.subTitle,
			description : this.props.description,
		}
		this.props.addPlaceToList(place)
	}

	markAsVisitedPlace(){
		const token = localStorage.getItem("token")
		axios.put(API +'/users/'+ this.props.userName +'/'+ this.props.match.params.id +'/places-visited/'+ this.props.id +'?',
		{},{
			headers: {
				Authorization: 'Bearer ' + token
			}
		})
		.then(response => {
			alert(response.data.message)
			this.setState({isVisited: true })
		})
		.catch(error => {
			alert(error.response.data.message)
		})
	}

	showUsersInterested(){
		this.setState({
			showUsersInterested : true,
		})
	}

	render() {
		return (
			<div>
				<Card style={{ width: '18rem' }}>
					<Card.Body>
						<Card.Title>{this.props.title}</Card.Title>
						<Card.Subtitle className="mb-2 text-muted">{this.props.subTitle}</Card.Subtitle>
						<Card.Text>
							{this.props.description}
						</Card.Text>
						{this.props.showUsersAdded ? (
							this.state.showUsersInterested ? (this.props.usersInterested.map(user => user + ', ')):
								(<Button variant="primary" onClick={this.showUsersInterested}>Show Users Interested</Button>)
						) : (this.props.showAddButon ?
							(<Button variant="primary" onClick={this.addPlace}>Add</Button>) :
							(!this.state.isVisited && <Button variant="primary" onClick={this.markAsVisitedPlace}>Visited</Button>)
						)}
					</Card.Body>
				</Card>
			</div>
		)
	}
}

export default withRouter(Place)
