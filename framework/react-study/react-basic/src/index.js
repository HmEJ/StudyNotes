//react的两个核心库
import React from 'react';
import ReactDOM from 'react-dom/client';
//根组件
import App from './App';
//把App组件渲染到root节点(public/index.html)上
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <App />
);


