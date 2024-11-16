import React, { useEffect, useReducer, useRef, useState } from 'react';
import './App.css';


const cities = ["Tokyo", "Tahoe", "Beijing", "New York", "San Francisco", "Seoul"];

function App() {
  const [emotion, setEmotion] = useState("Hello");
  const [checked, setChecked] = useReducer((checked) => !checked, false);
  const textTitle = useRef();
  const hexColor = useRef();

  const submit = (e) => {
    e.preventDefault();
    console.log("Form submitted");
    const title = textTitle.current.value;
    const color = hexColor.current.value;
    console.log(title, color);
  }

  useEffect(() => {
    console.log(`Current emotion is ${emotion}`);
  }, [emotion])

  return (
    <div className="App">
      <h1>
        Hello current emotion is {emotion}
      </h1>
      <form onSubmit={submit}>
        <button onClick={() => setEmotion("Happy")}>Happy.</button>
        <button onClick={() => setEmotion("Sad")}>Sad</button>
        <button onClick={() => setEmotion("Angry")}>Angry</button>
        <div>
          <input type="checkbox" checked={checked} onChange={setChecked} />
          {checked ? "checked" : "not checked"}
        </div>
        <div>
          <input ref={textTitle} type="text" placeholder="color title ...">
          </input>
          <input ref={hexColor} type="color" />
          <button>ADD</button>
        </div>

      </form>
    </div>
  );
}

export default App;
