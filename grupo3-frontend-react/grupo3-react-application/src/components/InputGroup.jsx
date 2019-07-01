import React, {Component} from 'react'
import Popup from 'reactjs-popup'
import axios from "axios";

class InputGroup extends Component {
	constructor(props) {
		super(props)
		this.state = {
			isVisited : false,
		}
	}

	render() {
		return(
			<div className = "card mb-2">
			</div>
		)
	}
}

export default InputGroup
