import React, { Component } from "react"
import { Form, Button, FormGroup, FormControl, Alert } from "react-bootstrap"
import CSSModules from "react-css-modules"
import axios from "axios"

import styles from "./signup_user.css"

class SignUp extends Component {
	constructor(props){
		super(props)
		this.state = {
			firstName : '',
			lastName : '',
			userName : '',
			password : '',
		}
	}

	validateForm() {
	  return this.state.userName.length > 0 && this.state.password.length > 0
	}

	handleChange = event => {
		this.setState({
			[event.target.id] : event.target.value
		})
	}
	
	handleSubmit = async event => {
		event.preventDefault()
		try {
			const user = {
				firstName: this.state.firstName,
				lastName: this.state.lastName,
				username: this.state.userName,
				password: this.state.password
			}

			axios.post(API+'/sign-up', user)
			.then(res => {
				this.props.updateProps('firstName', this.state.firstName)
				this.props.updateProps('lastName', this.state.lastName)
				this.props.updateProps('userName', this.state.userName)
				this.props.updateProps('password', this.state.password)
				alert(res.data.message)
				this.props.history.push("/login")
			}).catch(error => {
				alert(error.response.data.message)
			})

		} catch (error) {
			alert(error)
		}
	}

	render() {
		return (
			<div className="signup">
				<Form onSubmit={this.handleSubmit}>
					<h2>Sign up</h2>
					<Form.Group controlId="firstName">
						<Form.Control
							autoFocus
							value={this.state.firstName}
							onChange={this.handleChange}
							placeholder="First name"
						/>
					</Form.Group>
					<Form.Group controlId="lastName">
						<Form.Control
							autoFocus
							value={this.state.lastName}
							onChange={this.handleChange}
							placeholder="Last name"
						/>
					</Form.Group>
					<Form.Group controlId="userName">
						<Form.Control
							autoFocus
							type="text"
							value={this.state.userName}
							onChange={this.handleChange}
							placeholder="Username"
						/>
					</Form.Group>

					<Form.Group controlId="password">
						<Form.Control
							value={this.state.password}
							onChange={this.handleChange}
							type="password"
							placeholder="Password"
						/>
					</Form.Group>
					<Button
						disabled={!this.validateForm()}
						type="submit"
						variant="primary"
					>
						Sign Up
					</Button>
				</Form>
			</div>
		)
	}
}

export default CSSModules(SignUp, styles, { allowMultiple: true })
