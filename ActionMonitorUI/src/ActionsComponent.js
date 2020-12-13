import React from 'react'

const ActionsComponent = ({ actions }) => {

    let renderAction = (action) => {
        const { id, issuer, content } = action;
        return (
            <li key={id}>
                <div>
                    {id}---{issuer}---{content}
                </div>
            </li>
        );
    };

    return (
        <ul>
            {actions.map(action => renderAction(action))}
        </ul>
    )
}


export default ActionsComponent