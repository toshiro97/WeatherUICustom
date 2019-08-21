# WeatherUICustom

[![](https://jitpack.io/v/toshiro97/WeatherUICustom.svg)](https://jitpack.io/#toshiro97/WeatherUICustom)

***This library made by me , I use app today weather and I feel UI in this app so pretty. I custom some UI in the app with canvas. If it can help you please give me a star , I feel so happy 😂😂😂😂***
*First time I write library , I think it not very good but I will update version*
# Preview

![alt text](https://user-images.githubusercontent.com/29646525/63437586-0106d500-c455-11e9-898f-1ec110a9fc37.png)

# Implemention

*Add it in your root build.gradle at the end of repositories:*
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
*Add the dependency*
```
implementation 'com.github.toshiro97:WeatherUICustom:Tag'
```

## Setup XML 

**For air**


```
<com.toshiro.customview.AirIndicatorCustomView
            android:id="@+id/airIndicator"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_below="@id/tvAirStatus"
            app:progress_position="150"
            />
	    
```

**For Moon**

```
com.toshiro.customview.MoonCustomView
                android:id="@+id/moon1"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:layout_gravity="center"
                app:percent_moon="0.5"

                />

```

**For Sun**

```
update tomorrow

```

## Setup programmatically 

```
// air in 1-500 , follow air condition, and weatherbit
   airIndicator.updateProgress(150)

 // moon percent in 0-1,follow api weatherbit
    moon1.updateMoon(0.1)
    moon2.updateMoon(0.3)
    moon3.updateMoon(0.7)
    moon4.updateMoon(1.0)

```

## Authors

* **toshiro97** - *Initial work* - [Toshiro97](https://github.com/Toshiro97)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## About me
**Contact me**

*Facebook : https://www.facebook.com/toshiroisme*

*Gmail : nickevan9@gmail.com*

