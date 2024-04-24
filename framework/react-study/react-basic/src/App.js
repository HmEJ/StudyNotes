import { useRef } from "react";
const App = () => {
  const inputRef = useRef(null);
  const showDOM = () => {
    console.log(inputRef.current)
  }
  return (
    <div>
      <input
        type="text"
        ref={inputRef}
      />
      <button onClick={showDOM}>
        Focus
      </button>
    </div>
  );
}

export default App;
