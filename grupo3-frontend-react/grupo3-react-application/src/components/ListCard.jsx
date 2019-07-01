import React, {Component} from 'react'
import InputGroup from './InputGroup'
import EditButton from './EditButton'
import { Redirect } from "react-router-dom"
import {Button, Form, ButtonToolbar} from "react-bootstrap";
import axios from "axios";

class ListCard extends Component {
	constructor (props) {
	    super(props)
		this.state = {
	    	canEditList : false,
			newListName : props.cardName,
		}
		this.editListName = this.editListName.bind(this)
		this.deleteList = this.deleteList.bind(this)
	}

	handleSubmit = async event => {
		event.preventDefault()
		const token = localStorage.getItem("token")
		axios.put(API+'/users/'+ this.props.userName +'/list-of-places/'+ this.props.cardName +'/?new-name='+ this.state.newListName,
			{}, {
			headers: {
				Authorization: 'Bearer ' + token
			}
		})
		.then(res => {
			alert(res.data.message)
			this.setState({
				canEditList : false
			})
			this.props.updateListOfPlaces()
		})
		.catch(error => {
			alert(error.response.data.message)
		})
	}

	editListName(){
		this.setState({
			canEditList : true
		})
	}

	handleChange = event => {
		this.setState({
			[event.target.id]: event.target.value
		})
	}

	deleteList() {
		const token = localStorage.getItem("token")
		axios.delete(API+'/users/'+ this.props.userName +'/list-of-places/'+ this.props.cardName, {
				headers: {
					Authorization: 'Bearer ' + token
				}
			})
			.then(res => {
				alert(res.data.message)
				this.setState({
					canEditList : false
				})
				this.props.updateListOfPlaces()
			})
			.catch(error => {
				alert(error.response.data.message)
			})
	}

	render() {
		return (
			<div className = "col-md-4" >
				<div className = "card mt-4">
					<div className = "card-body">
						<div className = "row-md-2">
							{!this.state.canEditList ? (
								<div>
									<p>{this.props.cardName}</p>
									<ButtonToolbar>
										<EditButton editListName={this.editListName} />
										<Button variant="danger" className="button-delete" onClick={this.deleteList}>Delete</Button>
										<Button variant="link"
										        className="button-places"
										        onClick={() => this.props.history.push('/list/places/'+this.props.cardName)}>Places</Button>
									</ButtonToolbar>
								</div>
								) : (
								<div>
									<Form onSubmit={this.handleSubmit}>
										<Form.Group controlId="newListName">
											<Form.Control autoFocus
												value={this.state.newListName}
												onChange={this.handleChange}
											/>
										</Form.Group>
										<Button	type="submit" variant="primary">Update</Button>
									</Form>
								</div>
							)}
						</div>
					</div>
				</div>
			</div>
		)
	}
}

export default ListCard
