import React, {useState} from 'react';
import SockJsClient from 'react-stomp';
import ActionsComponent from './ActionsComponent';


const SOCKET_URL = 'http://localhost:8090/ws/';


const App = () => {
    const [actions, setActions] = useState([])

    let onConnected = () => {
        console.log("Connected!")
    };

    let onMessageReceived = (action) => {
        console.log('Received new action!!', action);
        setActions(actions.concat(action));
    };

    return (
        <div className="App">
            <SockJsClient
                url={SOCKET_URL}
                topics={['/actions']}
                onConnect={onConnected}
                onDisconnect={console.log("Disconnected!")}
                onMessage={msg => onMessageReceived(msg)}
                debug={false}
            />
            <ActionsComponent actions={actions}/>
        </div>
    )
};

export default App;
