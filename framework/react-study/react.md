# 概述

React由meta开发, 是一个用于**构建web和原生交互界面的库**

# 开发环境和项目初始

环境: node.js

创建一个react项目:

```bash
npx create-react-app <project-name>
```

> npm 和 npx 是 Node.js 生态系统中的两个关键工具
>
> **npx** 是 npm 5.2.0 版本引入的工具，用于执行 npm 包中的可执行文件。它的一个主要作用是临时调用包中的命令，而不需要全局安装这个包。

启动初始的项目:

```shell
npm start
```

核心文件:

- src/index.js   项目入口

  ```jsx
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
  ```

- src/App.js  

  ```jsx
  //项目的根组件
  // App -> index.js -> public/index.html(root) 
  function App() {
    return (
      <div className="App">
        this is App;
      </div>
    );
  }
  export default App;
  ```

# JSX

jsx = javascript + xml

## jsx本质

jsx是js的语法扩展, 浏览器本身不能识别. 需要通过**解析工具 (BABEL) 做解析**之后才能在浏览器中运行

## jsx中使用js表达式

在jsx中通过 `{}` 识别js中的**表达式**.

```jsx
const a = "red";

function App() {
  return (
    <div className="App">
      <div style={{color: a}}>
        this is red;
      </div>
    </div>
  );
}

export default App;
```

## 列表渲染

使用原生js中的map方法遍历渲染列表

注意: 需要一个key, key可以是index, 也可以是对象内部自身的key, 独一无二的就行.

```jsx
const list = [
  { name: 'apple', color: 'red' },
  { name: 'banana', color: 'yellow' },
  { name: 'grape', color: 'purple' },
  { name: 'orange', color: 'orange' },
  { name: 'watermelon', color: 'green' },
]

function App() {
  return (
    <div className="App">
      { list.map((item,index) => <li key={index}>{item.name}</li> ) }
      { list.map((item,index) => <li key={index}>{item.color}</li> ) }
    </div>
  );
}

export default App;
```

## 条件渲染

在react中, 通过 **逻辑与运算符(&&)**, **三元表达式(?:) **实现基础的条件渲染

```jsx
const isLogin = false;

function App() {
  return (
    <div className="App">
      {/* {isLogin && <h1>login</h1>} */}
      {isLogin ? <h1>login</h1> : <h1>logout</h1>}
    </div>
  );
} 

export default App;
```

### 复杂条件渲染

```jsx
const articleType = 0;

const getArticleTemplate = (articleType) => {
  if (articleType === 0) {
    return <div>无图模式</div>;
  } else if (articleType === 1) {
    return <div>单图模式</div>;
  } else {
    return <div>三图模式</div>;
  }
}

function App() {
  return (
    <div className="App">
        {getArticleTemplate(articleType)}
    </div>
  );
}

export default App;
```

## 基础事件绑定

语法:  on + 事件名称 = { 处理程序 }

```jsx
const handleClick = () => {
  console.log('Button clicked');
}

const App = () => {
  return (
    <div className="App">
        <button onClick={handleClick}>点我</button>
    </div>
  );
}

export default App;
```

### 获取事件参数

```jsx
const handleClick = (e) => {
  console.log('事件参数:',e);
}
```

### 传递自定义参数

注意, 一定是要在 `{}` 引用一个 箭头函数的 方法引用, 而不是直接引用方法

```jsx
const handleClick = (name,e) => {
  console.log('自定义参数:',name);
  console.log('事件参数:',e);
}

const App = () => {
  return (
    <div className="App">
        <button onClick={ (e) => handleClick("tom",e) }>点我</button>
    </div>
  );
}

export default App;

```

# react组件

一个组件就是用户界面的一部分, 它可以有自己的逻辑和外观, 组件之间可以互相嵌套, 也可以复用

在react中, 组件都是 **首字母大写** 的函数, 内部存放了组建的逻辑和视图UI, 渲染组件将组件当成标签写即可.

```jsx
const Button = () => {
  return (
    <button>Click me</button>
  )
}

const App = () => {
  return (
    <div className="App">
       <Button/>
    </div>
  );
}

export default App;
```

# useState

useState是react的hook函数, 允许我们向组件中添加一个 **状态变量** . 从而控制影响组件的渲染结果

本质: 和普通js变量不同的是, 状态变量一旦发生变化组件的视图ui也会跟着变化 ( 数据驱动视图 )

 ```jsx
 import { useState } from "react";
 
 const App = () => {
   //count:状态变量 , setCount:修改状态变量的方法, useState(0):状态变量的初始值
   const [count,setCount] = useState(0);
   const handleClick = () => {
     setCount(count + 1);
   }
   return (
     <div>
       <button onClick={handleClick}>{count}</button>
     </div>
   );
 }
 
 export default App;
 ```

## 修改状态的规则

在react中, 状态被认为是**只读**的, 我们始终应该去 **替换他**, 而不是去 **修改它**. 直接修改状态不会导致视图更新

说人话就是去调用 setXXX 方法去修改状态变量.

```jsx
import { useState } from "react";

const App = () => {
  //count:状态变量 , setCount:修改状态变量的方法, useState(0):状态变量的初始值
  const [obj,setObj] = useState({
    name: 'jack',
    age: 20
  });
  const handleClick = () => {
    setObj({
      ...obj,
      name: 'rose'
    });
  }
  return (
    <div>
      <button onClick={handleClick}>{JSON.stringify(obj)}</button>
    </div>
  );
}

export default App;
```

# 组件基础样式方案

1. 行内样式

   ```jsx
   const App = () => {
     return (
       <div>
         <div style={{color:'red', fontSize: '20px'}}>你好!</div>
       </div>
     );
   }
   
   export default App;
   ```

   或者

   ```jsx
   const style = {
     color: 'red',
     fontSize: '20px'
   }
   
   const App = () => {
     return (
       <div>
         <div style={style}>你好!</div>
       </div>
     );
   }
   
   export default App;
   ```

2. 类名控制

   ```jsx
   import './index.css'
   
   const App = () => {
     return (
       <div>
         <div className="foo">你好2!</div>
       </div>
     );
   }
   
   export default App;
   ```

# js工具库

## Lodash库 

Lodash 是一个一致性、模块化、高性能的 JavaScript 实用工具库。

提供对数组, 集合, 函数, 语言, 数学, 数字, 对象, 字符串等的高级方法封装.

官网: [Lodash中文文档(lodashjs.com)](https://www.lodashjs.com/)

安装lodash

```shell
npm i --save lodash
```

## classnames

A simple JavaScript utility for conditionally joining classNames together.

官方: [classnames(github.com)](https://github.com/JedWatson/classnames)

安装classnames:

```shell
npm install classnames
```

使用classnames之前:

```jsx
<div
    key={item.id}
    className={`nav-item ${type === item.type && 'active'}`}>
    {item.name}
</div>
```

使用classnames之后:

```jsx
<div
    key={item.id}
    className={classNames(`nav-item`,{active: type === item.type})}>
    {item.name}
</div>
```

## dayjs

Day.js是一个极简的JavaScript库，可以为现代浏览器解析、验证、操作和显示日期和时间。

官网: [Day.js中文网 (fenxianglu.cn)](https://dayjs.fenxianglu.cn/)

安装dayjs

```shell
npm install dayjs
```

# 受控表单绑定

1. 声明一个react状态

   ```jsx
   const [value,setValue] = useState('')
   ```

2. 通过value绑定状态, 通过onChange事件同步状态的值

   ```jsx
   <input
       value={value}
       type="text"
       onChange={(e) => setValue(e.target.value)}
   />
   ```

3. 示例

   ```jsx
   import { useState } from "react";
   const App = () => {
     const [value,setValue] = useState('')
     return (
       <div>
         <input
           value={value}
           type="text"
           onChange={(e) => setValue(e.target.value)}
         />
       </div>
     );
   }
   
   export default App;
   ```

# react获取DOM

使用 useRef 勾子函数

1. 使用useRef创建Ref对象, 与JSX绑定

   ```jsx
   const inputRef = useRef(null)
   ```

   ```jsx
   <input type='text' ref={inputRef} />
   ```

2. 在dom可用时, 通过current拿到dom对象 

   **需要dom生成之后才可用.**

   ```js
   console.log(inputRef.current)
   ```

3. 示例

   ```jsx
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
            click me
         </button>
       </div>
     );
   }
   
   export default App;
   ```

   