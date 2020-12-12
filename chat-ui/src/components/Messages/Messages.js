import React from 'react'

const Messages = ({ messages }) => {

    let renderMessage = (message) => {
        const { id, sender, content } = message;
        return (
            <li key={id}>
                <div>
                    {id}---{sender}---{content}

                </div>
            </li>
        );
    };

    return (
        <ul className="messages-list">
            {messages.map(msg => renderMessage(msg))}
        </ul>
    )
}


export default Messages