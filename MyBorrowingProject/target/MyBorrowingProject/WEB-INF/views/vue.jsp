<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

    <jsp:include  page="projectPages/header.jsp"/>

<style type="text/css">
.box{
  background-color: #ff0;
}
.textColor{
  color: #f00;
}
.textSize{
  font-size: 30px;
}
</style>
</head>
<body>

<!-- 使用v-show指令的元素始终会被渲染到HTML，它只是简单地为元素设置CSS的style属性 -->
<div id="v-show">
            <h1>Hello, Vue.js!</h1>
            <h1 v-show="yes">Yes!</h1>
            <h1 v-show="no">No!</h1>
            <h1 v-show="age >= 25">Age: {{ age }}</h1>
            <h1 v-show="name.indexOf('fool') >= 0">Name: {{ name }}</h1>
</div>

<hr>
<div id="app1">
  <p>{{ message }}</p>
</div>

<div id="app2">
  <span v-bind:title="message">
    鼠标悬停几秒钟查看此处动态绑定的提示信息！
  </span>
</div>
<hr>

<div id="class">
  <span class="box" :class="[isA?classA:'', isB?classB:'']">我是字</span>
</div>
<div id="if">
<div v-if="a > 0.5">
    Now you see me
</div>
<div v-else>
    Now you don't
</div>
</div>
<script>
    new Vue({
        el:'#if',
        data:{
            a:1
        }
    });
</script>

<div id="style">
  <span class="box" :style="styleObject">我也是字</span>
</div>
<ul id="wyx">
    <li v-for="n in evenNumbers">{{ n }}</li>
</ul>
<hr>
<div id="v-for">
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Sex</th>
                        <th>Index</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(person,index) in people">
                        <td>{{ person.name }} </td>
                        <td>{{ person.age  }}</td>
                        <td>{{ person.sex  }}</td>
                        <td>{{index}}</td>
                        <td><button v-on:click="myClick">给对象添加内容</button></td>
                    </tr>
                </tbody>
            </table>
    <div v-modle="a">{{a}}</div>

</div>

 <script>
        
        var vm = new Vue({
            el: '#v-show',
            data: {
                yes: true,
                no: true,
                age: 28,
                name: 'keepfool'
            }
        });
</script>
    
<script>
new Vue({
  el: '#app1',
  data: {
    message: 'Hello Vue.js!'
  }
});

var app2 = new Vue({
	  el: '#app2',
	  data: {
	    message: '页面加载于 ' + new Date().toLocaleString()
	  }
	});

</script>

<script>
new Vue({
	el:"#class",
	data:{
		classA:'textColor',
		classB:'textSize',
		isA:true,
		isB:true
	}
});
</script>

<script>
  new Vue({
    el: "#style",
    data: {
        styleObject:{
            color: 'red',
            fontSize: '40px',
            shadow: '5px 2px 6px #000'
        }
    }

});
  var wyx = new Vue({
      el:'#wyx',
      data: {
          numbers: [ 1, 2, 3, 4, 5 ]
      },
      computed: {
          evenNumbers: function () {
              return this.numbers.filter(function (aaaa) {
                  return aaaa % 2 === 0
              })
          }
      }
  })
</script>

<script>
        var vm = new Vue({
            el: '#v-for',
            data: {
                a:1,
                item:{
                    name:'Wyxabq',
                    age:24,
                    sex:'Male'
                },
                people: [{
                    name: 'Jack',
                    age: 30,
                    sex: 'Male'
                }, {
                    name: 'Bill',
                    age: 26,
                    sex: 'Male'
                }, {
                    name: 'Tracy',
                    age: 22,
                    sex: 'Female'
                }, {
                    name: 'Chris',
                    age: 36,
                    sex: 'Male'
                }]
            },
            created: function () {
                // `this` 指向 vm 实例
                console.log('a is: ' + this.a)
            },
           methods:{
            myClick:function(){
              this.people.push(this.item);
              console.log( this.people);
            }
           }
        });
    </script>
</body>
</html>