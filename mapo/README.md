# Mapo 

键值对Map和对象相互转换的类库。

## 使用

### 对象转Map

支持注解和继承

```
new Mapo().toMap(object);
```

| 注解 | 说明 |
|---|---|
| @SerializedName("") | 序列化字段名 |
| @Expose(serialize = true or false) | 是否序列化 |

和 `Gson` 的注解一模一样。

举例：

父类

```java
package com.ryw.jetpackexample;

import com.ryw.mapo.anotations.SerializedName;

public class BaseBean {

    @SerializedName("app_id")
    public String appId;

    @SerializedName("app_key")
    public String appKey;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

}

```

子类

```java
package com.ryw.jetpackexample;

import com.ryw.mapo.anotations.SerializedName;

public class GreetBean extends BaseBean{

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

```

使用

```java
GreetBean insertBean = new GreetBean();
insertBean.setFirstName("你好 *$#!^adsf");
insertBean.setLastName("formurl");
insertBean.setAppId("123456");
insertBean.setAppKey("dhaofanohkhjljk");
Mapo mapo = new Mapo();
Map<String, String> map = mapo.toMap(insertBean);
Log.e("Tag", mapo.toFormUrlString(map));
```

输出：

```
app_id=123456&app_key=dhaofanohkhjljk&first_name=你好 *$#!^adsf&last_name=formurl
```

##  Map转换成对象

客户端暂时用不到，以后再完善。
