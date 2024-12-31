# Vue模块化开发

传统方式:

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="vue.global.js"></script>
</head>

<body>
    <div id="app">
        {{ msg }}

        <h1> {{web.title}} </h1>
        <h1> {{web.url}} </h1>
    </div>

    <script>
        const {createApp,reactive} = Vue
        createApp({
            setup() {

                const web = reactive({
                    title: "测试",
                    url: "me.ahang.icu"
                })

                return {
                    msg: "success",
                    web
                }
            }
        }).mount("#app")
    </script>
</body>

</html>
```



下载 live server ，启动一个本地服务器来运行此代码。

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <div id="app">
        {{ msg }}

        <h1> {{web.title}} </h1>
        <h1> {{web.url}} </h1>
    </div>

    <script type="module">
        import {createApp,reactive} from './vue.esm-browser.js'
        createApp({
            setup() {

                const web = reactive({
                    title: "测试",
                    url: "me.ahang.icu"
                })

                return {
                    msg: "success",
                    web
                }
            }
        }).mount("#app")
    </script>
</body>

</html>
```

# ref和reactive

```js
const number = ref(10)
number.value = 20
```

```js
const web = reactive({
    title: "测试",
    url: "me.ahang.icu"
})
web.url = 'www.ahang.icu'
```

reactive**通常**用于存储复杂类型，如对象和数组。ref**通常**用于存储简单类型，比如数字和字符串。但是ref也可以用于存储对象和数组。

# v-on || @

```js
回车: <input type="text" @keyup.enter="add(40,60)"> <br>
空格: <input type="text" @keyup.space="add(20,30)"> <br>
tab: <input type="text" @keydown.tab="add(10,20)"> <br>
w: <input type="text" @keyup.w="add(5,10)"> <br>
ctrl+enter: <input type="text" @keyup.ctrl.enter="add(5,10)"> <br>
ctrl+a: <input type="text" @keyup.ctrl.a="add(5,10)"> <br>
```

# v-show || v-if

```html
<body>
    <div id="app">
        {{ web.show }}
        <hr>
        <button @click="toggle()">切换显示状态</button>
        <hr>
        <p v-show="web.show">你好，这是一段文字/.</p>
        <p v-if="web.show">你好，这是一段文字/.v-if</p>
    </div>

    <script type="module">
        import { createApp, reactive, ref } from './vue.esm-browser.js'
        createApp({
            setup() {
                const web = reactive({
                    show: true
                })
                const toggle = () => {
                    web.show = !web.show
                }
                return {
                    web,
                    toggle
                }
            }
        }).mount("#app")
    </script>
</body>
```

二者的区别：

v-show实现原理是通过css属性 display 来控制元素的渲染与否。而v-if则是简单粗暴的直接删除元素。

> 在实践应用中，对于需要频繁切换显示的元素，应当使用v-show。因为此时使用v-if会导致频繁从dom中删除和添加元素，带来额外的性能开销。

# v-bind || :

单向数据绑定

```html
<h3>value="djfijisdf.com"</h3>
<input type="text" v-bind:value="web.url">

<!-- 简写形式 -->
<h3>value="djfijisdf.com"</h3>
<input type="text" :value="web.url">
```

```html
<h3>class="test"</h3>
<b :class="{ test:web.fontStatus }">带带大师兄</b>

const web = reactive({
    url: "djfijisdf.comff",
    img: "window.png",
    fontStatus: true
})
```

# v-for

```html
<body>
    <div id="app">
        数组遍历
        <ul>
            <li v-for="(value,index) in data.number">
                value => {{ value }} ; index => {{ index }}
            </li>
        </ul>
        对象遍历
        <ul>
            <li v-for="(value, key) in data.user">
                key => {{ key }} ; value => {{ value }}
            </li>
        </ul>
        对象遍历2
        <ul>
            <li  v-for="(value, key, index) in data.user">
                index  => {{ index  }} ; key => {{ key }} ; value => {{ value }}
            </li>
        </ul>
        对象遍历3
        <ul>
            <template v-for="(value, key, index) in data.user">
                <li v-if="index == 1">
                    index  => {{ index  }} ; key => {{ key }} ; value => {{ value }}
                </li>
            </template>
        </ul>
        数组中包含对象遍历
        <ul>
            <li  v-for="(value, index) in data.teacher" :title="value.name" :key="value.id">
                index  => {{ index  }} ; id => {{ value.id }}
            </li>
        </ul>
    </div>

    <script type="module">
        import { createApp, reactive, ref } from './vue.esm-browser.js'
        createApp({
            setup() {

                const data = reactive({
                    number: [1, 2.4, 56, 77, 989],
                    user: {
                        name: "zhangsan",
                        gender: "male"
                    },
                    teacher: [
                        { id: 100, name: "lisi", gender: "male" },
                        { id: 101, name: "lisan", gender: "female" },
                    ]
                })

                return {
                    data
                }
            }
        }).mount("#app")
    </script>
</body>
```

# v-model

双向数据绑定

```html
<body>
    <div id="app">
        <h2>文本框 {{ data.text }}</h2>
        <h2>单选框 {{ data.radio }}</h2>
        <h2>复选框 {{ data.checkbox }}</h2>
        <h2>记住密码 {{ data.remember }}</h2>
        <h2>下拉框 {{ data.select }}</h2>

        单向数据绑定 <input type="text" :value="data.text"> <br>
        双向数据绑定 <input type="text" v-model="data.text"> <br>

        <input type="radio" v-model="data.radio" value="1"> 写作
        <input type="radio" v-model="data.radio" value="2"> 画画
        <hr>
        <input id="cb1" type="checkbox" v-model="data.checkbox" value="1"> <label for="cb1">篮球</label>
        <input id="cb2" type="checkbox" v-model="data.checkbox" value="2"> <label for="cb2">排球</label>
        <input id="cb3" type="checkbox" v-model="data.checkbox" value="3"> <label for="cb3">橄榄球</label>
        <input id="cb4" type="checkbox" v-model="data.checkbox" value="4"> <label for="cb4">足球</label>
        <hr>
        <input type="checkbox" v-model="data.remember"> 记住密码
        <hr>
        <select v-model="data.select">
            <option value="">请选择</option>
            <option value="a">中国</option>
            <option value="b">美国</option>
            <option value="c">韩国</option>
        </select>


    </div>

    <script type="module">
        import { createApp, reactive } from './vue.esm-browser.js'
        createApp({
            setup() {

                const data = reactive({
                    text: "www.ahang.com",
                    radio: "",
                    checkbox: [],
                    remember: false,
                    select: ""
                })


                return {
                    data
                }
            }
        }).mount("#app")
    </script>
</body>
```

# v-model修饰符

**延时渲染:**  当文本框失去焦点或者按下回车后才会渲染。

```html
实时渲染 <input type="text" v-model="data.url"> <br>
延时渲染 <input type="text" v-model.lazy="data.url"> <br>
```

**文本框值转为数字类型:**  当值以数字开头，则无视之后的字符串，转为数字类型。

```html
 <input type="text" v-model.number="data.user"> <br>
```

**去除首尾空格:**  去除输入框文本首尾空格

```html
<input type="text" v-model.trim="data.url"> <br>
```

# v-text 和 v-html

以文本方式渲染 或者 以html方式渲染。

```html
<body>
    <div id="app">
        <h3>{{ data.title }}</h3>
        <h4 v-text="data.title"></h4>
        <h3 v-html="data.url"></h3>
    </div>

    <script type="module">
        import { createApp, reactive } from './vue.esm-browser.js'
        createApp({
            setup() {
                const data = reactive({
                    title: "你好我的大头",
                    url: "<i style='color: red;'>ahang.icu</i>"
                })
                return {
                    data
                }
            }
        }).mount("#app")
    </script>
</body>
```

# computed

计算属性会缓存计算结果： 计算属性根据其依赖的响应式数据变化而重新计算。因此，善用计算属性可以提高性能。

```html
<body>
    <div id="app">
        <h3>add: {{ add() }}</h3>
        <h3>add: {{ add() }}</h3>

        <h3>sub: {{ sub }}</h3>
        <h3>sub: {{ sub }}</h3>

        x <input type="text" v-model.number="data.x"> <br>
        y <input type="text" v-model.number="data.y">
    </div>

    <script type="module">
        import { createApp, reactive, computed } from './vue.esm-browser.js'
        createApp({
            setup() {
                const data = reactive({
                    x: 10,
                    y: 20
                })

                let add = () => {
                    console.log("add")
                    return data.x + data.y
                }

                const sub = computed(() => {
                    console.log("sub")
                    return data.x - data.y
                })

                return {
                    data,
                    add,
                    sub
                }
            }
        }).mount("#app")
    </script>
</body>
```

# watch

监听常量或对象:  第一个参数是常量或者对象

```js
watch(hobby, (newVal,oldVal) => {
    console.log("newVal",newVal,"oldVal",oldVal)
    newVal == "b" && console.log("ok,你喜欢薯条.")
})
```

监听对象某个属性:  第一个参数是一个匿名函数，返回对象的属性

```js
watch(() => date.year, (newVal, oldVal) => {
    console.log("newVal", newVal, "oldVal", oldVal)
    date.year == "2025" && console.log("ok,你选择了2025")
})
```

# 自动侦听器 watchEffect

当监听的内容**发生变化**时，便会自动**执行watchEffect整个函数**。

因此，如果需要更加精细的监听，使用手动监听watch。

```js
watchEffect(()=>{
    console.log("--开始监听--")
    hobby.value == "a" && console.log("ok，你喜欢汉堡")
    date.year == "2025" && console.log("ok，2025")
    date.month == "8" && console.log("ok，8")
    console.log("--监听结束--")
})
```

# 图片轮播

实现原理很简单，通过将图片的地址和点击事件绑定即可。通过js的**模板语法**来控制图片的地址。

```html
<body>
    <div id="app">

        <h3> {{number}} </h3>

        <img class="img" :src=`image/${number}.jpg`> <hr>


        <button @click="pre()">上一张</button>
        <button @click="next()">下一张</button>

        <ul>
            <li v-for="value in 3">
                <a href="#" @click="jump(value)">{{value}}</a>
            </li>
        </ul>

    </div>

    <script type="module">
        import { createApp, reactive, watchEffect, ref } from './vue.esm-browser.js'
        createApp({
            setup() {

                const number = ref(1)

                const next = () => {
                    number.value ++
                    if(number.value == 4) number.value = 1
                }

                const pre = () => {
                    number.value --
                    if(number.value == 0) number.value = 3
                }

                const jump = (val) => {
                    number.value = val
                }


                return {
                    number,
                    next,
                    pre,
                    jump
                }
            }
        }).mount("#app")
    </script>
</body>
```

# 基于vite创建vue3项目

```shell
npm create vite@latest
```

# 父子通信

## 父传子

通过宏函数 `defineProps` 来实现。

`defineProps`用于接受父组件传来的值。其参数是**数组**或者**对象**。

1. 传字面量

   - 

   ```vue
   <script setup>
   import Footer from './components/foot.vue';
   </script>
   
   <template>
       <Footer number="100" />
   </template>
   ```

   - 子

   ```vue
   <script setup>
   const propsNum = defineProps(["number"])
   console.log(propsNum.number)
   </script>
   ```

2. 传入响应式基本数据

   - 父

   ```vue
   <script setup>
   import Footer from './components/foot.vue';
   import { ref } from 'vue';
   
   const num = ref(100)
   const changeNum = () => {
       num.value ++
   }
   </script>
   
   <template>
       <button @click="changeNum()">click</button>
       <Footer :propsNum="num" />
   </template>
   ```

   - 子

   ```vue
   <script setup>
   const number = defineProps(["propsNum"])
   console.log(number.propsNum)
   </script>
   
   <template>
   <h3>footer</h3> {{ number.propsNum }}
   </template>
   ```

3. 传响应式对象数据

   - 父

   ```vue
   <script setup>
   import { reactive } from 'vue';
   import Footer from './components/foot.vue';
   
   const user = reactive({
       name: "zhangsan",
       age: 30
   })
   const addAge = () => {
       user.age ++
   }
   </script>
   
   <template>
       <button @click="addAge()">click</button>
       <Footer :obj="user" />
   </template>
   ```

   - 子

   ```vue
   <script setup>
   const propsUser = defineProps(["obj"]) //数组形式接受
   console.log(propsUser.obj)
   </script>
   
   <template>
   <h3>footer</h3> {{ propsUser.obj }}
   </template>
   ```

   或

   ```vue
   <script setup>
   const propsUser = defineProps({  //对象形式接受
       obj: {
           type: Object
       }
   })
   console.log(propsUser.obj)
   </script>
   
   <template>
   <h3>footer</h3> {{ propsUser.obj.age }}
   </template>
   ```

## 子传父

通过宏函数`defineEmits`来实现。

其参数是数组, 表示要发射给父组件的事件

1. 子组件通过`defineEmits` 来定义发射的事件
2. 通过`emits`函数执行发射，向父组件发送数据

子：

```vue
<script setup>
const emits = defineEmits(["getWeb", "userAdd"])
emits("getWeb", {
    name: "ahang",
    url: "blog.ahang.icu"
})

const add = () => {
    emits("userAdd", 10)
}
</script>

<template>

    <h3>Header👇</h3>
    <button @click="add">添加用户</button>
    <h3>Header👆</h3>
    <br>
</template>

<style scoped></style>
```

父：

```vue
<script setup>
import { reactive, ref } from 'vue';
import Header from './components/header.vue';

const web = reactive({
    name: "ahang",
    url: "me.ahang.icu"
})

const emitsGetWeb = (data) => {
    console.log(data);
    web.url = data.url
}

const emitsUserAdd = (data) => {
    console.log(data);
    user.value += data
}

const user = ref(0)
</script>

<template>
	<!-- 在子组件标签中通过 `@<事件名>` 来指定处理的方法。在方法中处理接收到的数据 -->	
    <Header @getWeb="emitsGetWeb" @userAdd="emitsUserAdd"/>
    {{ web.url }} - {{ user }}
</template>

```

# 跨组件通信

原理： 

**父组件**通过`provide`方法将数据 (对象，字面量，函数以及响应式数据) 传递给旗下的**所有子组件**。任何子组件都可以通过`inject`注入依赖来获取数据。

> 注意： provide只能由上而下传递。也就是说只能由父组件传递给子组件

top组件：

```vue
<script setup>
import { ref, provide } from 'vue';
import Header from './components/header.vue'

const web = {
    name: "ahang",
    url: "me.ahang.icu"
}
provide("provideWeb",web)
</script>

<template>
    <Header></Header>
</template>
```

middle组件:

```vue
<script setup>
import Nav from './nav.vue';
</script>

<template>
    <Nav/>
</template>
```

bottom组件 (bottom组件可以获取到top组件传来的数据):

```vue
<script setup>
import { inject } from 'vue';
const web = inject("provideWeb")
console.log(web);
</script>
```

# 插槽

插槽(slot) 

可以在父组件中自定义模板片段，在子组件中可以将定义的模板片段插入到子组件的特定位置

## 匿名插槽

父：

```vue
<script setup>
import Header from './components/header.vue';
</script>


<template>
    <h3>App.vue</h3>
    <Header>
        <a href="me.ahang.icu">阿航的博客</a>  <!--自定义模板片段-->
    </Header>
</template>

```

子：

```vue
<script setup>

</script>

<template>
    <h4>header.vue</h4>
    <slot/>  <!--使用匿名插槽来获取片段-->
</template>
```

## 具名插槽

用`v-slot`来标记一个具名插槽 简写为 `#`

父：

```vue
<template>
    <Footer>
        <template v-slot:url>
            <a href="me.ahang.icu">网址</a>
        </template>
    </Footer>
</template>
```

或

```vue
<template>
    <Footer>
        <template #url>
            <a href="me.ahang.icu">网址</a>
        </template>
    </Footer>
</template>
```

---

子：

```vue
<template>
    <h4>footer.vue</h4>
    <slot name="url"/>
</template>
```

# 作用域插槽

子组件向父组件传递数据，并在父组件定义的模板中渲染

实现：

1. 子组件通过在slot标签中自定义属性来传递数据
2. 父组件在插槽中的自定义模板上通过`v-slot`来接受数据

子：

```vue
<slot name="url" title="阿航的网站" user="1000"/>
```

父:

```vue
<Footer>
    <!-- <template #url="data">  普通写法-->
    <template #url="{title,user}">   <!-- 解构的写法 -->
        {{ title }} <br>
        {{ user }} <br>
        <a href="me.ahang.icu">网址</a>
    </template>
</Footer>
```

# 生命周期函数

1. 挂载阶段
   - `onBeforeMount`
   - `onMounted`
2. 更新阶段
   - `onBeforeUpdate`
   - `onUpdated`
3. 卸载阶段
   - `onBeforeUnmount`
   - `onUnmounted`
4. 错误处理
   - `onEooroCaptured`

# toRef 和 toRefs

`toRefs`将一个响应式对象的所有属性转为ref对象

`toRef`将一个响应式对象的某个属性转为ref变量

考虑以下代码：

```vue
<script setup>
import { reactive,toRefs,toRef } from 'vue';

let {name,url} = reactive({
    name: "zhangsan",
    url: "zhangsan.com"
})

console.log(typeof url);
</script>

<template>
    {{ url }}
</template>

```

我们将一个响应式对象进行结构，通过log查看解构出的url的类型，发现是一个普通的字符串类型。也就是说，响应式对象解构出来会变成一个普通的变量。

通过`toRefs`我们可以将响应式对象的所有属性都解构为响应式变量

```vue
<script setup>
import { reactive,toRefs,toRef } from 'vue';

let web = reactive({
    name: "zhangsan",
    url: "zhangsan.com"
})

let {name,url} = toRefs(web)

console.log(url);
</script>
```

此时，可以通过控制台输出得知这个url已经是一个响应式的变量了。

`toRef`方法则是可以将一个响应式对象的某个属性解构为一个响应式变量

```vue
<script setup>
import { reactive,toRefs,toRef } from 'vue';

let web = reactive({
    name: "zhangsan",
    url: "zhangsan.com"
})

let url = toRef(web,"url")

console.log(url);
</script>
```

# Pinia

pinia是一个轻量级的状态管理库

1. 安装pinia

```shell
npm install pinia
```

2. 在main.js中创建pinia实例

```js
import { createPinia } from 'pinia'
const pinia = createPinia()
app.use(pinia)
app.mount('#app')
```

3. 在src下创建一个stores目录
4. 在stores目录下创建pinia存储库

通过`defineStore`宏函数来创建存储库。`defineStore`第一个参数是仓库id (String)，必须是唯一的。第二个参数是仓库的定义。可以用选项式 (Vue2写法) 或者 `setup`函数 (Vue3写法)

```js
// ==> src/stores/web.js
import {reactive,ref} from 'vue';
import {defineStore} from 'pinia';

export const useWebStore = defineStore("web", ()=>{
    const web = reactive({
        name: "ahang",
        url: "me.ahang.icu"
    })
    
    const users = ref(1000)
    
    const userAdd = () => {
        users.value++
    }
    return {
        web,
        users,
        userAdd
    }
})
```

5. 在其他vue组件中可以使用该存储库

首先需要导入该存储库, 之后便可以只用仓库中的数据和状态

```vue
<script setup>
	import { useWebStore } from './stores/web'
    const webStore = useWebStore()
    console.log(webStore.web.url)
    console.log(webStore.web.name)
    console.log(webStore.users)
</script>

<template>
{{ webStore.web.url }}
{{ webStore.users }}
<button @click="webStore.userAdd"> 添加用户 </button>
</template>
```

# Pinia持久化存储插件

[Pinia Plugin Persistedstate](https://prazdevs.github.io/pinia-plugin-persistedstate)

将状态保存到`localStorage`中

1. 安装插件

```shell
npm i pinia-plugin-persistedstate
```

2. 将插件添加到pinia实例

```js
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)
```

3. 使用

   在定义仓库时，`defineStore `添加第三个参数，启用持久化

```js
import { defineStore } from "pinia";
import { reactive, ref } from 'vue';

export const useWebStore = defineStore("web", () => {
    const web = reactive({
        title: "ahang",
        url: "me.ahang.icu"
    })
    const users = ref(1000)
    const userAdd = () => {
        users.value++
    }
    return {
        web,
        users,
        userAdd
    }
}, {
    persist: true
})
```

# axios封装

