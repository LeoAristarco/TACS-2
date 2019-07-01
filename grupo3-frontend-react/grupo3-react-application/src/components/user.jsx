import React, { Component } from "react"
import { Route, Redirect, Switch, withRouter } from "react-router-dom"
import {Button, Card} from "react-bootstrap"
import axios from "axios";

class User extends Component {
	constructor(props) {
		super(props)
		this.state = {
		}
	}

	render() {
		return (
			<div>
				<Card style={{ width: '18rem' }}>
					<Card.Body>
						<Card.Title>{this.props.userName}</Card.Title>
						<Card.Text>
							<b>Amount of Lists : </b>{this.props.amountOfLists}<br />
							<b>Amount of visited places : </b>{this.props.amountOfVisitedPlaces}<br />
							<b>Last Acess : </b>{this.props.lastAccess || 0}
						</Card.Text>
					</Card.Body>
				</Card>
			</div>
		)
	}
}

export default withRouter(User)