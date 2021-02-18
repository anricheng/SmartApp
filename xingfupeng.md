## Handler

 Handler主要用于异步消息的处理 ,他的工作原理 **当发出一个消息之后，首先进入一个消息队列，发送消息的函数即刻返回，而另外一个部分逐个的在消息队列（messageQueue)中将消息取出，然后对消息进行出来，就是发送消息和接收消息不是同步的处理**。 这种机制通常用来处理相对耗时比较长的操作 



Handler整个流程

首先创建一个消息或者事务，通过Bundle构建实例对象使用putXXX的方法将消息先添加到bundle然后使用message.setDat额（）的放将消息添加到message中  最后是通过handler将这些通过sendMessage()的方法send到messageQueue队列中

这儿对messageQueue简单描述下  结尾能知道这是一个队列，而且是先进先出的方式排列，所有我们知道它是通过尾插的方式将消息加载到到这个队列中  而且在其他线程也能看得到这个队列

![1612863891502](https://github.com/anricheng/JTPLearning/blob/main/picture2/1612842436024.png)

但是MessageQueue只是数据存储结构，而不能对数据进行操作  这个队列中的数据去除需要另一个角色

在加入队列后一个比较重要的主角Looper

在Looper中他的工作原理通过查看源代码知道他是一个for(;;)循环，但是是一个死循环，这样可用不断的加载message不断的取，只要队列有消息就能被获取到

 在一个线程中运行的消息循环。线程默认情况下是没有与之管理的消息循环的。
要创建一个消息循环，在线程中调用prepare，然后调用loop。即开始处理消息，直到循环停止。大多数情况下通过Handler来与消息循环互动 

```
class LooperThread extends Thread {
    public Handler mHandler;
    public void run() {
        Looper.prepare(); 
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
            }
        };
        Looper.loop();
    }
}
```

  创建Handler实例，Handler会获取当前线程的Looper

如果实例化Handler时当前线程没有Looper，会报异常 RuntimeException

 调用了`Looper.loop()`之后，looper开始运行。当looper的messageQueue中没有消息时，loop方法里面有一个死循环。queue.next()方法是可能会阻塞线程的。如果从queue中获取到null，则表明此消息队列正在退出。此时looper的死循环也会被返回。 

```
 for (;;) {
            Message msg = queue.next(); // might block
            if (msg == null) {
                return;
            }
```

 调用looper的quit方法，实际上调用了`mQueue.quit(false)`。消息队列退出后，looper的loop死循环也被退出了。 

```
Message next() {
        for (;;) {
            nativePollOnce(ptr, nextPollTimeoutMillis);
```

 MessageQueue的next方法  没有消息时，这个死循环会阻塞在`nativePollOnce`这个方法 



### Looper中的属性

 Looper持有MessageQueue；唯一的主线程Looper sMainLooper；Looper当前线程mThread； 存储Looper的sThreadLocal  ThreadLocal并不是线程，它的作用是可以在每个线程中存储数据。 



#### 初始化消息队列

 在Looper构造器中即创建了一个MessageQueue，**Looper持有消息队列的实例** 

#### 发送消息

 通过Looper.prepare初始化好消息队列后就可以调用Looper.loop进入消息循环了，然后我们就可以向消息队列发送消息， 消息循环就会取出消息进行处理，在看消息处理之前，先看一下消息是怎么被添加到消息队列的 

#### 消息循环

 MessageQueue的成员mMessages中，Native层的消息都保存在了Native Looper的 mMessageEnvelopes中，这就可以说有两个消息队列，而且都是按时间排列的 



### Handler的使用方法：

 void handleMessage( Message msg):处理消息的方法 

final boolean hasMessage(int what):检查消息队列中是否包含有what属性为指定值的消息

final boolean hasMessage(int what ,Object object) :检查消息队列中是否包含有what跟object属性指定值的消息

sendEmptyMessage(int what):发送空消息

final Boolean send EmptyMessageDelayed(int what ,long delayMillis):指定多少毫秒发送空消息

final boolean sendMessage(Message msg):立即发送消息

这个方法使用的会比较多

final boolean sendMessageDelayed(Message msg,long delayMillis):多少秒之后发送消息



三个比较重要的对象

1Handler：它把消息发送给Looper管理的MessageQueue,并负责处理Looper分给它的消息

2.MessageQueue：管理Message，由Looper管理

3.Looper：每个线程只有一个Looper，比如UI线程中，系统会默认的初始化一个Looper对象，它负责管理MessageQueue，不断的从MessageQueue中取消息，并将相对应的消息分给Handler处理。



### Message

 Message 属于被传递，被使用的角色。Message 是包含描述和任意数据对象的“消息”，能被发送给handler ， Message包含2个int属性和一个额外的对象 , 获取实例最好的办法是调用message.obtain()  每个Message都持有Handler实例。如果Handler持有Activity的引用，Activity onDestroy后Message却仍然在队列中，因为Handler与Activity的强关联，会造成Activity无法被GC回收，导致内存泄露。因此在Activity onDestroy 时，与Activity关联的Handler应清除它的队列由Activity产生的任务，避免内存泄露 



## ThreadLocal

主要用于多线程下对共享资源的使用，共享资源在同时被修改是会产生数据不一致， 为了保证线程安全，一般使用者在访问共享变量的时候需要进行额外的同步措施才能保证线程安全性。  ThreadLocal是除了加锁这种同步方式之外的一种保证一种规避多线程访问出现线程不安全的方法 ， 当我们在创建一个变量后，如果每个线程对其进行访问的时候访问的都是线程自己的变量这样就不会存在线程不安全问题 



 ThreadLocal的接口方法 

 void set(Object value)   设置当前线程的线程局部变量的值。 

 public Object get()   该方法返回当前线程所对应的线程局部变量。 

 public void remove()   将当前线程局部变量的值删除，目的是为了减少内存的占用 

 protected Object initialValue()   返回该线程局部变量的初始值 ， 该方法是一个protected的方法  是一个延迟调用方法，在线程第1次调用get()或set(Object)时才执行，并且仅执行1次。ThreadLocal中的缺省实现直接返回一个null 

 与Thread同步机制的比较 

 在同步机制中，通过对象的锁机制保证同一时间只有一个线程访问变量。这时该变量是多个线程共享的，使用同步机制要求程序慎密地分析什么时候对变量进行读写，什么时候需要锁定某个对象，什么时候释放对象锁等繁杂的问题，程序设计和编写难度相对较大。 

 ThreadLocal则从另一个角度来解决多线程的并发访问ThreadLocal会为每一个线程提供一个独立的变量副本 隔离了多个线程对数据的访问冲突。因为每一个线程都拥有自己的变量副本，从而也就没有必要对该变量进行同步了。ThreadLocal提供了线程安全的共享对象，在编写多线程代码时，可以把不安全的变量封装进ThreadLocal。 

 对于多线程资源共享的问题，同步机制采用了“以时间换空间”的方式，而ThreadLocal采用了“以空间换时间”的方式。前者仅提供一份变量，让不同的线程排队访问，而后者为每一个线程都提供了一份变量，因此可以同时访问而互不影响。

锁机制主要是让资源在同一时间只能有一个人对他进行操作 ，ThreadLocal是通过给每一个人一个变量，最后通过空间合并的方式将最终的结果统一

 Synchronized用于线程间的数据共享，而ThreadLocal则用于线程间的数据隔离。所以ThreadLocal的应用场合，最适合的是按线程多实例（每个线程对应一个实例）的对象的访问 

ThreadLocalMap是ThreadLocal类的一个静态内部类，它实现了键值对的设置和获取（对比Map对象来理解），每个线程中都有一个独立的ThreadLocalMap副本，它所存储的值，只能被当前线程读取和修改。ThreadLocal类通过操作每一个线程特有的ThreadLocalMap副本，从而实现了变量访问在不同线程中的隔离。因为每个线程的变量都是自己特有的，完全不会有并发错误。还有一点就是，ThreadLocalMap存储的键值对中的键是this对象指向的ThreadLocal对象，值就是你所设置的对象了 



## 开关组件 ToggleButton和开关Switch 

 ToggleButton与开关switch也是由button按钮派生出来的，因此他们的本质也是按钮，button支持的各种属性，方法toggleButton和switch也适用 

### ToggleButton

 属性 

> - **android:disabledAlpha**：设置按钮在禁用时的透明度
> - **android:textOff：**按钮没有被选中时显示的文字
> - **android:textOn：**按钮被选中时显示的文字 另外，除了这个我们还可以自己写个selector，然后设置下Background属性即可

### Switch(开关)

```
android:showText：设置on/off的时候是否显示文字,boolean
android:splitTrack：是否设置一个间隙，让滑块与底部图片分隔,boolean
android:switchMinWidth：设置开关的最小宽度
android:switchPadding：设置滑块内文字的间隔
android:switchTextAppearance：设置开关的文字外观
android:textOff：按钮没有被选中时显示的文字
android:textOn：按钮被选中时显示的文字
android:textStyle：文字风格，粗体，斜体写划线那些
android:track：底部的图片
android:thumb：滑块的图片
android:typeface：设置字体，默认支持这三种:sans, serif, monospace;除此以外还可以使用 其他字体文件(*.ttf)
```

首先要写两个个drawable文件

第一个 **thumb_selctor.xml** 

```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_pressed="true" android:drawable="@drawable/switch_btn_pressed"/>
    <item android:state_pressed="false" android:drawable="@drawable/switch_btn_normal"/>
</selector>
```

第二个命名为 **track_selctor.xml:**

```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_checked="true" android:drawable="@drawable/switch_btn_bg_green"/>
    <item android:state_checked="false" android:drawable="@drawable/switch_btn_bg_white"/>
</selector>
```

这两个.xml文件是为了配置显示按钮图片



在 **activity_main.xml** 中布局

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <ToggleButton
        android:id="@+id/tbtn_open"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:checked="true"
        android:textOff="关闭声音"
        android:textOn="打开声音" />

    <Switch
        android:id="@+id/swh_status"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textOff=""
        android:textOn=""
        android:thumb="@drawable/thumb_selctor"
        android:track="@drawable/track_selctor" />

</LinearLayout>
```

ToggleButtopn中主要设置按钮的静态的资源，就是按钮是透明，没有选中时的文字显示  以及textOn被选中时后的文字显示



Switch中主要的设置按钮的其他属性，如showText设置是否显示文字

 spiltTrack：**是否设置一个间隙，让滑块与底部图片分隔

switchMinWidth  :设置最小宽度

android:switchPadding：设置滑块内文字的间隔

*android:switchTextAppearance：**设置开关的文字外观，暂时没发现有什么用...

android:textOff：**按钮没有被选中时显示的文字

android:textOn：**按钮被选中时显示的文字

android:textStyle：**文字风格，粗体，斜体写划线那些

android:track：**底部的图片

android:thumb：**滑块的图片

 android:typeface：**设置字体，默认支持这三种:sans, serif, monospace 

在MainActivity中写逻辑代码





#### drawable文件

 android的drawable文件一共可以有 ： drawable-ldpi(低密度)        drawable-mdpi(中等密度)       drawable-hdpi(高密度)     drawable-xhdpi(超高密度)  drawable-xxhdpi(超超高密度)   drawable-xxxhdpi(超超超高密度)   drawable-nohdpi(无缩放) 



 比如在一个中等分辨率的手机上，Android就会选择drawable-mdpi文件夹下的图片，文件夹下有这张图就会优先被使用，在这种情况下，图片是不会被缩放的 

 但是如果没有在drawable-mdpi的文件夹下找到相应图片的话，Android系统会首先从更高一级的drawable-hdpi文件夹中查找，如果找到图片资源就进行缩放处理，显示在屏幕上 

如果drawable-hdpi文件夹下也没有的话，就依次往drawable-xhdpi文件夹、drawable-xxhdpi文件夹、drawable-xxxhdpi文件夹、drawable-nodpi；

如果更高密度的文件夹里都没有找到，就往更低密度的文件夹里寻找，drawable-ldpi文件夹下查找；

如果都没找到，最终会在默认的drawable文件夹中寻找，如果默认的drawable文件夹中也没有那就会报错啦。（前提是把一张图片做成很多不同的分辨率放在各个对应密度的drawable文件夹下）

![1612842436024](https://github.com/anricheng/JTPLearning/blob/main/picture2/1612863891502.png)



## ProgressBar(进度条)

 ProgressBar继承与View类，直接子类有AbsSeekBar和ContentLoadingProgressBar， 其中AbsSeekBar的子类有SeekBar和RatingBar 



 **常用属性** 

```

 android:max：进度条的最大值
 android:progress：进度条已完成进度值
 android:progressDrawable：设置轨道对应的Drawable对象
 android:indeterminate：如果设置成true，则进度条不精确显示进度
 android:indeterminateDrawable：设置不显示进度的进度条的Drawable对象
 android:indeterminateDuration：设置不精确显示进度的持续时间
 android:secondaryProgress：二级进度条，类似于视频播放的一条是当前播放进度，一条是缓冲进度，前者通过progress属性进行设置！


```



这个组件中常用方法

> - **getMax**()：返回这个进度条的范围的上限
> - **getProgress**()：返回进度
> - **getSecondaryProgress**()：返回次要进度
> - **incrementProgressBy**(int diff)：指定增加的进度
> - **isIndeterminate**()：指示进度条是否在不确定模式下
> - **setIndeterminate**(boolean indeterminate)：设置不确定模式下



配置ProgressBar圆形的进度头条

这儿配置了三个  配置了一个最小的，一个正常的和一个大的

```
 <ProgressBar
        style="@android:style/Widget.ProgressBar.Small"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <ProgressBar
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <ProgressBar
        style="@android:style/Widget.ProgressBar.Large"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

```

然后再配置两个水平形的进度条

```
<ProgressBar
        style="@android:style/Widget.ProgressBar.Horizontal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:max="100"
        android:progress="18" />

    <ProgressBar
        style="@android:style/Widget.ProgressBar.Horizontal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:indeterminate="true" />
```



然后就是写布局文件activity.xml

```
class MainActivity : AppCompatActivity() {
    private var img_pgbar: ImageView? = null
    private var ad: AnimationDrawable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        img_pgbar = findViewById<View>(R.id.img_pgbar) as ImageView
        ad = img_pgbar.getDrawable()
        img_pgbar.postDelayed(Runnable { ad!!.start() }, 100)
    }
}
```





# Android学习  2021/2/10

## RecyclerView的学习和使用

这个布局出现中和了之前安卓开发的几种布局方式，其中ListView,Grid View和瀑布布局模式SwaggerView现在使用一种那就是RecyclerView就可以包括所有的模式，因为 RecyclerView可以自定义布局管理器来决定item的排布规则 ，所以可以代替之前的布局

​    **1.获取RecyclerView对象 。**
​     **2.初始化数据 。**
​     **3.适配器实例化 。**
​     **4.设置LayoutManager**
​     **5. 设置Adapter 。** 

使用前第一步当然是导入依赖

 **在Gradle添加RecyclerView的依赖** 

![1612936808612](https://github.com/anricheng/JTPLearning/blob/main/picture2/20210201.png)

使用<menu>和<item>布局文件

![1612943866111](https://github.com/anricheng/JTPLearning/blob/main/picture2/20210202.png)



创建Activity布局文件

![1612943918364](https://github.com/anricheng/JTPLearning/blob/main/picture2/20210203.png)



加载配置参数

![1612943954998](https://github.com/anricheng/JTPLearning/blob/main/picture2/20210204.png)



编写适配器Adapter  实现方法

![1612951650374](https://github.com/anricheng/JTPLearning/blob/main/picture2/20210205.png)

onCreateViewHolder用于创建条目View

getItemCount用于返回条目条数

![1612952601279](https://github.com/anricheng/JTPLearning/blob/main/picture2/20210206.png)



RecyclerView看起来使用会比之前规定好的ListView复杂些，因为他需要自己去定义，但是这正是他的优势之处，可以根据自己的需求去调整

优点：替代Listview和GridView，

既可以加载列表也可以加载表格

支持瀑布流这样高级的显示方式.

内置了强大的垃圾回收机制 

.规范了Viewholder的使用，



## 三种适配器

 适配器（adapter）在android中是数据和视图（View）之间的一个桥梁，通过适配器以便于数据在view视图上显示 

##  **ArrayAdapter** 

使用流程

#### 在布局文件中创建listview控件

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <ListView
        android:id="@+id/listview"
        android:layout_width="match_parent"
        android:layout_height="match_parent" >
    </ListView>
</LinearLayout>
```

### 声明数据源

数据源可以是写在文件里的或者数据库获取以及临时测试的数据

```
private  itemData：String = arrayof{ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}
```

### 初始化适配器

```
var adapter:ArrayAdapter = ArrayAdapter<String>(ArrayAdapterActiity.this, android.R.layout.simple_expandable_list_item_1, itemData);
```

###  绑定适配器

```
listView.Adapter(adapter);
```



##  **SimpleAdapter** 

 个简单的适配器,既为简单,就只是被设计来做简单的应用的,比如静态数据的绑定,不过仍然有自定义的空间  可以将静态数据映射到XML文件中定义好的视图。你可以指定由Map组成的List(比如ArrayList)类型的数据 ， 在ArrayList中的每个条目对应List中的一行。Maps包含每一行的数据。你可以指定一个XML布局以指定每一行的视图，根据Map中的数据映射关键字到指定的视图 

 绑定数据到视图分两个阶段 

 首先，如果设置了SimpleAdapter.ViewBinder，那么这个设置的ViewBinder的setViewValue(android.view.View, Object, String)将被调用。如果setViewValue的返回值是true，则表示绑定已经完成,将不再调用系统默认的绑定实现 

 如果返回值为false，视图将按以下顺序绑定数据：
 如果View实现了Checkable（例如CheckBox），期望绑定值是一个布尔类型。
 TextView.期望绑定值是一个字符串类型，通过调用setViewText(TextView, String)绑定。
 ImageView,期望绑定值是一个资源id或者一个字符串，通过调用setViewImage(ImageView, int) 或 setViewImage(ImageView, String)绑定数据 

```
SimpleAdapter(context, data, resource, from, to)
```

首先构造器中context表示上下文， 表示访问整个android应用程序接口，基本上所有的组件都需要 

 data：需要展示的数据源，是map组成的list集合，每个Map对应ListView中的一行，每一个Map（键——值对）中的键名必须包含在from中所指定的键 

 resource：展示的布局文件，可以自己在layout设置xml文件去设置需要展示 的样式 ， 表示界面布局的id 表示该文件作为列表项的组件 

 from：Map中的键名 ， 该Map对象的哪些key对应value来生成列表项 

 to：绑定数据视图的ID，与from成对应的关系 

##  **BaseAdapter** 

 **为抽象类  为我们 自定义的适配器** 

 最基础的Adapter，也就是说，它可以做所有的事情。所以为什么说最实用最常用，原因就在于它的全能性，不会像ArrayAdapter等的封装好的类有那么多局限性 

```css
listView.Adapter(mBaseAdapter);
```

接下来跟之前一样继承 BaseAdapter 

重写里面的实现方法

![1612955227131](https://github.com/anricheng/JTPLearning/blob/main/picture2/20210207.png)

新建一个适配器对象

```
var mBaseAdapter: MyBaseAdapter = MyBaseAdapter()
```

实现的方法分别作用

getCount : 要绑定的条目的数目，比如格子的数量

getItem : 根据一个索引（位置）获得该位置的对象

getItemId : 获取条目的id

getView : 获取该条目要显示的界面



## PagedAdapter

PagerAdapter主要是viewpager的适配器，而viewPager则也是在android.support.v4扩展包中新添加的一个强大的控件，可以实现控件的滑动效果

一样的去使用这个接口就要实现方法

如果继承pageradapter，必须重写下面的四个方法 

1 instantiateItem(ViewGroup, int)  

 这个方法，return一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中 

2. destroyItem(ViewGroup, int, Object) 

    这个方法，是从ViewGroup中移出当前View 

3. getCount()  

    这个方法，是获取当前窗体界面数 

4. isViewFromObject(View, Object) 

    这个方法用于判断是否由对象生成界面 

它在后台线程中完成大部分工作，所以不会给主线程带来负担 

```
internal class MyPagerAdapter : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any { //当前滑动到的ViewPager页面
        val view: View = viewList.get(position)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) { //每次划出当前页面的时候就销毁
        //super.destroyItem(container, position, object);
        container.removeView(viewList.get(position))
    }

    override fun getCount(): Int { //设置ViewPager有几个滑动页面
        return imgRes.length //用图片资源数组的length也可以，用图片容器的size也可以
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` //官方固定写法
    }
}
```

 PagerAdapter比AdapterView的使用更加普通.ViewPager使用回调函数来表示一个更新的步骤 

 在需要的时候pageradapter也可以实现视图的回收 

 viewpager不直接处理每一个视图而是将各个视图与一个键联系起来。这个键用来跟踪且唯一代表一个页面，该键还独立于这个页面所在adapter的位置。当pageradapter将要改变的时候他会调用startUpdate函数，接下来会调用一次或多次的instantiateItem或者destroyItem。最后在更新的后期会调用finishUpdate。当finishUpdate返回时 instantiateItem返回的对象应该添加到父ViewGroup  destroyItem返回的对象应该被ViewGroup删除。methodisViewFromObject(View,  Object)代表了当前的页面是否与给定的键相关联 

pageradapter支持数据集合的改变，数据集合的改变必须要在主线程里面执行，然后还要调用notifyDataSetChanged方法 



# Android        2021/2/12

## 数据库

Android内置了一个名为SQLite的关系型数据库，这是一款轻量型的数据库，操作十分简便。SQLite与别的数据库不同的是，它没有数据类型。可以保存任何类型的数据到你所想要保存的任何表的任何列中。但它又支持常见的类型比如: NULL, VARCHAR, TEXT, INTEGER, BLOB, CLOB...等。

唯一的例外是：integer primary key 此字段只能存储64位整数。

在JAVA项目中，要使用JDBC操作数据库需要加载数据库驱动，连接数据库等操作。Android简化了我们的数据库操作，无需由我们进行数据库驱动加载、连接等操作。



 Android中进行数据库操作，需要涉及到几个类 ：

###  SQLiteOpenHelper ：

 这是一个抽象的帮助类，用于管理数据库的创建及版本维护操作 

实现抽象方法其中onCreate有四个参数

context：应用的上下文对象

name：要操作的数据库的名称

factory：cursor工厂类对象，一般指定为null

version：数据库的版本号，必须大于等于1，由于控制数据库的升级。



###  SQLiteDatabase ：

 通过SQLiteOpenHelper对象获取SQLiteDatabase对象后，便可以调用SQLiteDatabase类的相关方法进行数据库的增删改查操作了 

常用的方法：

1execSQL(sql:String)

2execSQL(sql:String,bindArgs:Object[])

 execSQL()方法用于执行SQL语句，可以用于执行不需要返回值的一些数据库操作 rawQuery()



如何创建一个数据库

 创建一个名为BookStore.db的数据库, 然后在数据库中新建一张Book表, 其中有id(主键), 作者, 价格,页数和书名 

 新建一个创建表的kotlin类 

![1613221501537](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613221501537.png)

![1613221512040](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613221512040.png)

继承SQLiteOpenHelpe

实现里面的两个方法onCreate，onUpgrade，

在.xml文件中创建一个button用于点击创建数据库

![1613226072775](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613226072775.png)

onCreate中四个参数分别的意思是（context，数据库名，null(这里也允许是一个自定义的Cursor，他会在查询到数据之后返回)，当前数据库的版本号） 

另外会用到的两个方法

getReadableDatabase()，以读的方式得到一个数据库实例

getWritableDatabase()，以写的方式打开一个数据库实例

 当我们去new这个数据库的时候，其实只是拿到了这个数据库的操作句柄 

 后我们去拿着这个实例再去得到读数据库的实例，当在这里的时候他就会判断，如果数据库不存在，就onCreate()，如果存在就去判断版本是不是当前版本，如果我们在new的时候传入的版本比之前已经存在的数据库版本高，那么就会调用onUpgrade()这个方法去更新数据库，所以更新数据库的逻辑需要我们自己去写 

建表语句

```
create table Book(
    id integer primary key autoincrement,
    author text,
    price real,
    pages integer,
    name text)
```





# Android    2021/2/18

### Button点击事件的三种实现

首先创建一个button

第一种方式就是自己定义一个方法，但是方法的参数应该是v:View

返回值为空，要和实现接口后实现的方法保存一致

通过onClick指定当点击时去执行那个方法



第二种方法就是刚刚说的实现接口里面的onClick方法



第三种方式就是当这个点击方法我们只会用到一次的时候尽量使用内部类的实现方式

```
btn.setOnClickListener({    v->Log.v("ttt","匿名内部类实现登录点击事件")})
```

![1613657734125](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613657734125.png)



### RadioButton

RadioButton是在做单选框的时候用到的一种控件，一般在使用的时候需要再RadioGroup中使用，因为我们平时看到的选择一般都不只有一个



怎么做一个点击响应事件

首先再RadioGroup中方两个RadioButton

![1613658005176](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613658005176.png)

因为需要对RadioButton进行响应识别，所有这儿的RadioGroup和

RadioButton都必须要id属性

通过id找到RadioGroup,实现接口RadioGroup.OnCheckedChangeListener实现onCheckedChanged方法

![1613658189067](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613658189067.png)

启动应用

![1613658311555](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613658311555.png)

![1613658320168](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613658320168.png)

这就是怎么使用单选框，单选框必须在RadioGroup中使用，需要实现onCheckedChanged方法，里面两个参数

```
group: RadioGroup?, checkedId: Int
```

第一个参数就是这个RadioButton组，第二个是选中的id



### ImageView

使用ImageView可以作为视图控件，为视图添加图片

首先将要使用的图片放在mipmap中，注意开发应用不同存放位置不同，手机就放在手机的，平板的就对应放在平板存放文件

![1613658595348](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613658595348.png)

这儿注意的是background和src的区别

当使用background的时候图片会被拉伸，因为要适应整个屏幕

而src的时候图片也很被拉伸，但是是长宽同时进行拉伸，根据最小的值进行拉伸



### CheckBox

CheckBox是复选框，顾名思义可以同时选中的选项栏，

在acyivity.xml视图配置文件中定义四个复选框

![1613658783395](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613658783395.png)

实现CompoundButton.OnCheckedChangeListener接口实现

```
onCheckedChanged
```

方法，这儿会跟前面radiobuton中的方法名一样，但是其本质是完全不同的

![1613658975622](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613658975622.png)

记得box3.setOnCheckedChangeListener(this)设置

这儿展示的是实现接口的方法，接下来看看内部类的例子

![1613659029191](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613659029191.png)

这样也可以达到相同的功能，对于这三种达到响应监听的方法没有什么就是特别实用的，的看环境来巧妙运用

就想这儿使用内部类就没有实现方法好一些，因为这儿实现方法一次就能搞定，而内部类却需要写四次



### AlertDialog

 AlertDialog可以在当前活动界面弹出一个对话框，用于提示一些重要信息或是警告内容。

​    AlertDialog置于所有页面元素之上，能够屏蔽其他控件的交互。

​    由于AlertDialog的构造方法被声明为protected，所以我们不能使用new来创建AlertDialog对象。

​    Android为我们提供另外一个类AlertDialog.Builder，用它可以创建AlertDialog对象实例，用show()方法显示。

 AlertDialog有几个基本方法 :

 setTitile() setMessage() setCancelable() setPositiveButton() setNegativeButton() setNeutralButton() 

![1613659280598](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613659280598.png)

定义一个按钮作为点击提示对话框

![1613659358407](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613659358407.png)

这儿我们用的点击事件就是在视图配置的时候直接绑定点击响应的方法

创建一个AlertDialog.Builder的对象

设置提示框的title 图标 和信息通过

```
builder.setTitle("对话框").setIcon(R.mipmap.ic_launcher).setMessage("一起去看流星雨吗？")
```

一般提示框都会有两个选择，yes/no这样的提示

这儿对应的是两个方法setPositiveButton和setNegativeButton根据方法的字面意思很好理解一个积极的肯定就说yes,这儿我们设置ok,另一个就说消极的这儿我们设置不行

第二个参数是点击时的监听

最后记得使用这个builder对象去创建AlertDialog还有使用show()方法让它显示出来

![1613659849360](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613659849360.png)

![1613659856695](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613659856695.png)

![1613659863087](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613659863087.png)

这就是一个AlertDialog的简单使用



### 使用intent传值

在使用它之前需要两个Activity而且必须是已经在AndroidManifest.xml中已经进行了注册的

创建一个按钮当点击时执行传值的方法

在MainActivity中创建一个Intent的实例对象

通过setClass的方法指定要传值的Activity和需要接受的Activity

```
intent.setClass(this, Class.forName("SecondActivity"))
```

通过putExtra方法将值放入intent，第一个是标记传送的身份，这里面可以放多种对象

```
intent.putExtra("name","haha")
```

记得传值的最后一步开启传送

```
startActivity(intent)
```

![1613660042261](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1613660042261.png)

在另一个接受的Activity中

使用getXXXExtra()的方法取得对应的值

```
var intent:Intent = intentvar age:Int = intent.getIntExtra("age",0)Log.i("SecondActivity", age.toString())
```

