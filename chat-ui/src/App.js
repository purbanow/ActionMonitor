import React, {useState} from 'react';
import SockJsClient from 'react-stomp';
import './App.css';
import Messages from './components/Messages/Messages';


const SOCKET_URL = 'http://localhost:8090/ws-chat/';

const App = () => {
    const [messages, setMessages] = useState([])

    let onConnected = () => {
        console.log("Connected!!")
    }

    let onMessageReceived = (msg) => {
        console.log('New Message Received!!', msg);
        setMessages(messages.concat(msg));
    }

    return (
        <div className="App">
            <SockJsClient
                url={SOCKET_URL}
                topics={['/topic/group']}
                onConnect={onConnected}
                onDisconnect={console.log("Disconnected!")}
                onMessage={msg => onMessageReceived(msg)}
                debug={true}
            />
            <Messages
                messages={messages}
            />
        </div>
    )
}

export default App;
