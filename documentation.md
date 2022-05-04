
# Music Visualiser Project

| Course | Name | Student Number |
|------|--------|------------------|
|TU856 | Raghd Al Juma | D19125768 |
|TU856 | Laura Andrews | D21125370 |
|TU856 | Stephen Moore | D21125383 |


🐶🐾🎈👻👽🤡🤖  

😛🤓😁😂🤣 

😄😍🙂🤗 

😏😎💀 

🍌🦍 

🎶 

## Description of the assignment
The music visuals assignment, a program that works on visual graphics based on a pre-loaded audio. In our project, there is three scenes created by each member of the group in which each of us created visuals that react to the choosen song, "Different Heaven & EH!DE - My Heart [NCS Release]". 

These visuals were generated based on knowledge gained in classes or by internet research. Among the three scenes there are elements that are responive to the sound either by changing colours, shapes, or size. 

## Instructions
(need to check this) The files for this assignemnt are located in java/src/ie/tudublin of the Music Visuals-2 folder. You can run our assignment from the Main.java file. 

## How it works
(need to check this) Once the file is ran from the main, a fullscreen processing window is display where the song and a loading bar is displayed. When the loading bar is completed, the first scene is shown.


The second scene:

The second scene has 3 *crazy* planets which rotate around themselves/each other. Around the planets are hoops that consist of small circles , these hoops change size and color depending of the music played, they also rotate around the planet.

In the drawPlanet method, the method takes in three parameters (x , y position of the planet and size). The planet position is set using the function translate(x, y). The color of the Planet is set using ambientLight(). this function works with (red, green blue) RGB colors to make the planets color. also directionalLight() function is used to add light to only one side of the planet to make it look more real. When code first run , the planet is darker as it is dim, when the user press the mouse, the planet lights up.

Also to make the planet rotate, the rotateX, rotateY, rotateZ functions are used and to draw the planet I used sphere() function.The hoop are drawn using for loop and circles as well as the rotate function. 

rotate speed depends on the mapped value of i in for loop from 0 to the size of the audio buffer. and the circles size and position on the x axis depends on the value in the lerpedBuffer array list.
The code:
```Java
void drawPlanet(float s, float g, float size) 
    { 

        //position of planet
        translate(s, g);
        if(mousePressed)
        {
            lights();
            ambientLight(200, 20, 250);
            directionalLight(255, 60, 126, -1, 0, 0);
        }
        
        ambientLight(100, 20, 100);
        directionalLight(51, 102, 126, -1, 0, 0);
        
        //rotate planet on x, y , z axis
        rotateY((float) (frameCount/100.0));
        rotateX((float) (frameCount/50.0));
        rotateZ((float) (frameCount/50.0));

        //fill
        fill(100);
        noStroke();

        //draw sphere
        sphere(size);

        //loop through buffer and draw the hoops around the planet
        for(int i = 0 ; i < audioBuffer.size()/2 ; i ++)
        {
            
            float c = map(i, 0, audioBuffer.size(), 0, 255);
            float f = lerpedBuffer[i] * height/2 * 4.0f;

            noStroke();
            
            //rotate the hoops around planet
            rotate(c);
            fill(s, f, c);

            //draw hoops
            circle(f*0.6f , i, f*0.4f);
            circle(f * 0.8f, i, f *0.4f);  
        }
    }
 ```   

## What I am most proud of in the assignment

## Markdown Tutorial

This is *emphasis*

This is a bulleted list

- Item
- Item

This is a numbered list

1. Item
1. Item

This is a [hyperlink](http://bryanduggan.org)

# Headings
## Headings
#### Headings
##### Headings

This is code:

```Java
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

So is this without specifying the language:

```
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

This is an image using a relative URL:

![An image](images/p8.png)

This is an image using an absolute URL:

![A different image](https://bryanduggandotorg.files.wordpress.com/2019/02/infinite-forms-00045.png?w=595&h=&zoom=2)

This is a youtube video:

[![YouTube](http://img.youtube.com/vi/J2kHSSFA4NU/0.jpg)](https://www.youtube.com/watch?v=J2kHSSFA4NU)

This is a table:

| Heading 1 | Heading 2 |
|-----------|-----------|
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |

