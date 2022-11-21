#pragma once
#include <SFML/Graphics.hpp>
#include <iostream>
#include "SettingsMgr.h"

using namespace std;
using namespace sf;

class SettingsUI
{
private:
	CircleShape cyancircles;
	CircleShape greencircles;
	CircleShape redcircles;
	CircleShape circlesshape;
	RectangleShape rektshape { {42.f, 42.f} };
	RectangleShape eraser{ {50.f, 35.f} };
	SettingsMgr* othermgr;

public:
		// settings UI needs a pointer to SettingsMgr so that it can
	// get the current Settings properties
	SettingsUI(SettingsMgr *mgr)
	{
		othermgr = mgr;
	}

	// Check to see if the position indicated by the mouse argument
	// is over one of the buttons, and if so, process it.
	void handleMouseUp(Vector2f cursor)
	{
		if (cyancircles.getGlobalBounds().contains(cursor))
		{
			cout << "Cursor is in the Cyan Circle. " << endl;
			othermgr->setCurColor(Color::Cyan);
			cyancircles.setFillColor(Color::Cyan);
			greencircles.setFillColor(Color::White);
			redcircles.setFillColor(Color::White);
			eraser.setFillColor(Color::White);
		}

		if (greencircles.getGlobalBounds().contains(cursor))
		{
			cout << "Cursor is in the Green Circle. " << endl;
			othermgr->setCurColor(Color::Green);
			greencircles.setFillColor(Color::Green);
			cyancircles.setFillColor(Color::White);
			redcircles.setFillColor(Color::White);
			eraser.setFillColor(Color::White);
		}

		if (redcircles.getGlobalBounds().contains(cursor))
		{
			cout << "Cursor is in the Red Circle. " << endl;
			othermgr->setCurColor(Color::Red);
			redcircles.setFillColor(Color::Red);
			cyancircles.setFillColor(Color::White);
			greencircles.setFillColor(Color::White);
			eraser.setFillColor(Color::White);
		}

		if (circlesshape.getGlobalBounds().contains(cursor))
		{
			cout << "Cursor is in the Circle Shape. " << endl;
			othermgr->setCurShape(SettingsMgr::CIRCLE);
			circlesshape.setFillColor(Color::Black);
			rektshape.setFillColor(Color::White);
			eraser.setFillColor(Color::White);
		}

		if (rektshape.getGlobalBounds().contains(cursor))
		{
			cout << "Cursor is in the Square Shape. " << endl;
			othermgr->setCurShape(SettingsMgr::SQUARE);
			rektshape.setFillColor(Color::Black);
			circlesshape.setFillColor(Color::White);
			eraser.setFillColor(Color::White);
		}

		if (eraser.getGlobalBounds().contains(cursor))
		{
			cout << "Cursor is in the eraser. " << endl;
			othermgr->setCurShape(SettingsMgr::SQUARE);
			othermgr->setCurColor(Color::White);
			eraser.setFillColor(Color::Magenta);
			cyancircles.setFillColor(Color::White);
			greencircles.setFillColor(Color::White);
			redcircles.setFillColor(Color::White);
			circlesshape.setFillColor(Color::White);
			rektshape.setFillColor(Color::White);
		}
	}

	void die(string msg)
	{
		cout << msg << endl;
		exit(-1);
	}

	// render the left hand part of the window
	void draw(RenderWindow& win)
	{
		RectangleShape options{ {230.f,580.f} };
		options.setFillColor(Color::White);
		options.setPosition({ 10.f, 10.f });
		win.draw(options);

		cyancircles.setRadius(20);
		cyancircles.setOutlineColor(Color::Cyan);
		cyancircles.setOutlineThickness({ 5.f });
		cyancircles.setPosition({ 100.f,100.f });
		win.draw(cyancircles);

		greencircles.setRadius(20);
		greencircles.setOutlineColor(Color::Green);
		greencircles.setOutlineThickness({ 5.f });
		greencircles.setPosition({ 100.f,170.f });
		win.draw(greencircles);

		redcircles.setRadius(20);
		redcircles.setOutlineColor(Color::Red);
		redcircles.setOutlineThickness({ 5.f });
		redcircles.setPosition({ 100.f,240.f });
		win.draw(redcircles);

		circlesshape.setRadius(20);
		circlesshape.setOutlineColor(Color::Black);
		circlesshape.setOutlineThickness({ 5.f });
		circlesshape.setPosition({ 150.f,400.f });
		win.draw(circlesshape);

		rektshape.setOutlineColor(Color::Black);
		rektshape.setOutlineThickness({ 5.f });
		rektshape.setPosition({ 50.f,400.f });
		win.draw(rektshape);

		eraser.setOutlineColor(Color::Magenta);
		eraser.setOutlineThickness({ 5.f });
		eraser.setPosition({ 100.f,525.f });
		win.draw(eraser);

		Font font;
		if (!font.loadFromFile("C:\\Windows\\Fonts\\arial.ttf"))
		{
			die("couldn't load font");
		}
		Text colortitle("Drawing Color", font, 25);
		colortitle.setFillColor(Color::Black);
		colortitle.setPosition({ 45.f, 50.f });
		win.draw(colortitle);

		Text shapetitle("Shape", font, 25);
		shapetitle.setFillColor(Color::Black);
		shapetitle.setPosition({ 90.f, 350.f });
		win.draw(shapetitle);

		Text eraser("Eraser", font, 25);
		eraser.setFillColor(Color::Black);
		eraser.setPosition({ 90.f,475.f });
		win.draw(eraser);
	}
};
