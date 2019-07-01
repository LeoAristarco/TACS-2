import React, {Component} from 'react'
import InputGroup from './InputGroup'

class PlaceCard extends Component {
	constructor (props) {
	    super(props)
	    this.state = {
		    markedAsVisited : false,
	    }
	}

	render() {
		return(
			<div className = "col-md-4" >
				<div className = "card mt-4">
					<div className = "card-header">
						<h3>{this.props.cardName}</h3>
						<span className= "badge badge-pill badge-danger ml-2">Flag</span>
						<span className="badge badge-pill badge-warning">Star</span>
						<i className="fa fa-star"></i>
					</div>

				    <div className = "card-body">
						<InputGroup label="Save"
						            placeId={this.props.id}
						            userName={this.props.userName}
						/>
			            <p><mark>some description</mark></p>
			            <p>id: { this.props.id }</p>
				    </div>
				</div>
			</div>
		)
	}
}

export default PlaceCard
