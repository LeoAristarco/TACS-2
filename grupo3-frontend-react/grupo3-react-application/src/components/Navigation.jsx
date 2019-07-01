import React, {Component} from 'react'
import {Button} from "react-bootstrap"

class Navigation extends Component {
	render() {
		return(
			<nav className = "navbar navbar-dark bg-dark">
				<Button variant="dark" onClick={() => this.props.history.push('/home')}>{this.props.title}</Button>
					<div className = "row-md-2">
					<div className="input-group">
					</div>
				</div>
			</nav>
		)
	}
}

export default Navigation
