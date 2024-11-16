import { useEffect, useReducer, useState } from 'react';
import './App.css';


const cities = ["Tokyo", "Tahoe", "Beijing", "New York", "San Francisco", "Seoul"];

function App() {
  const [emotion, setEmotion] = useState("Hello");
  const [checked, setChecked] = useReducer((checked) => !checked, false);

  useEffect(() => {
    console.log(`Current emotion is ${emotion}`);
  }, [emotion])

  return (
    <div className="App">
      <h1>
        Hello current emotion is {emotion}
      </h1>
      <button onClick={() => setEmotion("Happy")}>Happy.</button>
      <button onClick={() => setEmotion("Sad")}>Sad</button>
      <button onClick={() => setEmotion("Angry")}>Angry</button>
      <div>
        <input type="checkbox" checked={checked} onChange={setChecked} />
        {checked ? "checked" : "not checked"}
      </div>
    </div>
  );
}

export default App;
