/*
===============================================
| Nathan A Lalli                              |
| Friday, April 17th                          |
| Programming Assignment #7                   |
| Desccription: Space Invaders                |
===============================================
*/

#include <iostream>
#include <SFML/Graphics.hpp>
#include <list>
#include "Alien.h"
#include "Alien2.h"
#include "AlienList.h"
#include "AlienList2.h"
#include "Bomb.h"
#include "BombList.h"
#include "Missile.h"
#include "MissileList.h"
#include "Ship.h"

using namespace sf;
using namespace std;

void updateCounter(Text& counterText, int value)
{
	string counterString = to_string(value);
	counterText.setString(counterString);
}

int main()
{
	bool touchleft = true;
	bool touchright = false;

	const int win_WIDTH = 800;
	const int win_HEIGHT = 600;
		
	Ship ship;
	AlienList alienlist;
	AlienList2 alienlist2;
	MissileList missilelist;
	BombList bomblist;
	
	RenderWindow win(VideoMode(win_WIDTH, win_HEIGHT), "aliens!");

	win.setFramerateLimit(60);
	
	Texture starsTexture;
	if (!starsTexture.loadFromFile("stars.jpg"))
	{
		cout << "Unable to load stars texture!" << endl;
		exit(EXIT_FAILURE);
	}
	
	Texture missileTexture;
	if (!missileTexture.loadFromFile("missile.png"))
	{
		cout << "Unable to load missile texture!" << endl;
		exit(EXIT_FAILURE);
	}
	
	Texture alientexture;
	if (!alientexture.loadFromFile("rocket_ship.png"))
	{
		cout << "Unable to load alien texture!" << endl;
		exit(EXIT_FAILURE);
	}

	Texture alientexture2;
	if (!alientexture2.loadFromFile("space_ship.png"))
	{
		cout << "Unable to load alien texture!" << endl;
		exit(EXIT_FAILURE);
	}

	Texture logo;
	if (!logo.loadFromFile("logo.png"))
	{
		cout << "Unable to load logo texture! Dying now...";
		exit(EXIT_FAILURE);
	}
	
	Texture button;	
	if (!button.loadFromFile("start_button.png"))
	{
		cout << "Unable to button enemy texture! Dying now...";
		exit(EXIT_FAILURE);
	}
	
	Texture bombtexture;
	if (!bombtexture.loadFromFile("missile.png"))
	{
		cout << "Unable to load bomb texture! Dying now...";
		exit(EXIT_FAILURE);
	}

	Font font;
	if (!font.loadFromFile("C://Windows//Fonts//Arial.ttf"))
	{
		cout << "couldn't load font, dying..." << endl;
		exit(EXIT_FAILURE);
	}

	Text counterText("0", font, 20);
	counterText.setPosition(775, 5);
	
	ship.setText();
	
	Sprite background;
	background.setTexture(starsTexture);
	background.setScale(1.5, 1.5);

	Sprite startbutton;
	startbutton.setTexture(button);
	startbutton.setPosition(250, 400);

	Sprite titlelogo;
	titlelogo.setTexture(logo);
	titlelogo.setPosition(200, 25);

	ship.setposition(win);

	bool titlescreen = true;
	bool amhit = false;
	bool firing = false;
	bool isMissileInFlight = false;
	
	int timer = 0;
	int timer2 = 0;
	int count = 0;
	int currenttime = 0;
	int random = 0;
	int lastcounter = 0;
	int level = 1;

	while (win.isOpen())
	{

		Event event;

		Vector2f shiploc = ship.getsprite().getPosition();

		while (win.pollEvent(event))
		{

			if (event.type == Event::Closed)
			{
				win.close();
			}

			else if (titlescreen && event.type == Event::MouseButtonReleased)
			{
				Vector2f mousePos = win.mapPixelToCoords(Mouse::getPosition(win));
	
				if (startbutton.getGlobalBounds().contains(mousePos))
				{
					titlescreen = false;
				}
			}

			else if (event.type == Event::KeyPressed)
			{
				if (event.key.code == Keyboard::Space)
				{
					isMissileInFlight = true;
					missilelist.createmissile(missileTexture, shiploc);
					if (isMissileInFlight)
					{
					}
				}
			}
		}

			win.draw(background);
	
			if (titlescreen)
			{
				win.draw(titlelogo);
				win.draw(startbutton);
			}
			else if (!amhit || level != 3)
			{

				if (level == 1)
				{
					alienlist.createalien(alientexture);

					timer2 = (timer2 + 1) % 60;
					if (timer2 == 0)
					{
						timer++;
						currenttime++;
						cout << timer << endl;
					}
						
					ship.movement();
	
					ship.draw(win);
	
					Vector2f alienloc;
					alienloc.x = 10;
					alienloc.y = 20;
	
					alienlist.display(win);
	
					missilelist.display(win);
					missilelist.missileoffscreen();
	
					alienlist.movement(timer);
					alienlist.killed(count, missilelist, level);
	
					if (lastcounter != count)
					{
						updateCounter(counterText, count);
					}
	
					win.draw(counterText);
	
					random = bomblist.droptime(1, 7);
	
					if (currenttime == random && !firing)
					{
						alienlist.dropbombs(bombtexture, win, bomblist, count);
						currenttime -= random;
						firing = true;
					}
					if (firing)
					{
						bomblist.display(win);
					}
	
					bomblist.bomboffscreen(firing);
		
					bomblist.hitmarker(ship.bounds(), amhit);
				}

				else if (level == 2)
				{	
					alienlist2.createalien(alientexture2);
					timer2 = (timer2 + 1) % 60;
					if (timer2 == 0)
					{
						timer++;
						currenttime++;
						cout << timer << endl;
					}
	
					ship.movement();
	
					ship.draw(win);
	
					Vector2f alienloc;
					alienloc.x = 10;
					alienloc.y = 20;
	
					alienlist2.display(win);
	
	
					missilelist.display(win);
					missilelist.missileoffscreen();
					
					alienlist2.movement(timer);
					alienlist2.killed(count, missilelist, level);
					
					if (lastcounter != count)
					{
						updateCounter(counterText, count);
					}
					
					win.draw(counterText);
					
					random = bomblist.droptime(1, 7);
					
					if (currenttime == random && !firing)
					{
						alienlist2.dropbombs(bombtexture, win, bomblist, count);
						currenttime -= random;
						firing = true;
					}
					if (firing)
					{
						bomblist.display(win);
					}
	
					bomblist.bomboffscreen(firing);
	
					bomblist.hitmarker(ship.bounds(), amhit);
				}
			}

			win.display();
	}
	return 0;
}