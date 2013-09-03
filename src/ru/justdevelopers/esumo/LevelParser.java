package ru.justdevelopers.esumo;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import ru.justdevelopers.esumo.characters.FatCat;
import ru.justdevelopers.esumo.characters.JunkCat;
import ru.justdevelopers.esumo.characters.Protagonist;
import ru.justdevelopers.esumo.characters.SmellyCat;
import ru.justdevelopers.esumo.characters.TeddyCat;

import android.content.Context;

public class LevelParser {
	
	public void loadlevel(Context ctx) {
		try {
			/* Create a URL we want to load some xml-data from. */
			InputStream is = ctx.getResources().openRawResource(R.raw.pack1);

			/* Get a SAXParser from the SAXPArserFactory. */
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();

			/* Get the XMLReader of the SAXParser we created. */
			XMLReader xr = sp.getXMLReader();
			
			/* Create a new ContentHandler and apply it to the XML-Reader */
			ParsingHandler myExampleHandler = new ParsingHandler();
			xr.setContentHandler(myExampleHandler);

			/* Parse the xml-data from our URL. */
			xr.parse(new InputSource(is));

		} catch (Exception e) {
		}
	}
	

	class ParsingHandler extends DefaultHandler {
		private String st;
		private Circle circle;
		boolean continueRead= false;
		
		@Override
		public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
			if (qName.equals(st)) continueRead = false;
		}
		
		@Override
		public void startElement(String namespaceURI, String localName,	String qName, Attributes atts) throws SAXException {
			st = "lvl"+Integer.toString(GameActivity.CURRENT_LEVEL);

			if (qName.equals(st)) {
				setBg(atts.getValue("background"));
				continueRead = true;
			}
				
			if (qName.equals("character") && continueRead) {
				String character  =  atts.getValue("type");
				int x = (int) (Double.parseDouble(atts.getValue("x")) * Main.width);  // should i do that, or just use Main.width locally? 
				int y = (int) (Double.parseDouble(atts.getValue("y")) * Main.height);
				int r;
				if (atts.getValue("r").equals("default")) r = 60; 
				else r = Integer.parseInt(atts.getValue("r"));
//				System.out.println("DO PREOBRAZOVANIYA "+r);
				r = (int)(r* Main.resCoef);
//				System.out.println("POSLE PREOBRAZOVANIYA "+r);
				System.out.println();
				createCharacter(character, x, y, r);

			}
		
		}
		private void createCharacter(String character, int x, int y, int r){
			circle = null;
			if (character.equals("Protagonist")) {circle = new Protagonist(x,y,r);}
			if (character.equals("SmellyCat")) {circle = new SmellyCat(x, y,r);}
			if (character.equals("FatCat")) {circle = new FatCat(x, y, r);}
			if (character.equals("TeddyCat")) {circle = new TeddyCat(x, y,r);}
			if (character.equals("JunkCat")) {circle = new JunkCat(x, y,r);}
			if (circle != null) Circle.characters.add(circle);
			else System.out.println("~~~ you forgot to add new character to LevelParser.createCharacter!");
		}
		
		//Switch-case?
		private void setBg(String bg){
			if (bg.equals("@drawable/lvl0bg")) GameActivity.BG_IMAGE=R.drawable.lvl0bg;
			if (bg.equals("lvl1bg")) GameActivity.BG_IMAGE=R.drawable.lvl1bg;
			if (bg.equals("lvl2bg")) GameActivity.BG_IMAGE=R.drawable.lvl2bg;
			if (bg.equals("lvl3bg")) GameActivity.BG_IMAGE=R.drawable.lvl3bg;
			if (bg.equals("lvl4bg")) GameActivity.BG_IMAGE=R.drawable.lvl4bg;
			if (bg.equals("lvl5bg")) GameActivity.BG_IMAGE=R.drawable.lvl5bg;
			if (bg.equals("lvl6bg")) GameActivity.BG_IMAGE=R.drawable.lvl6bg;
			if (bg.equals("lvl7bg")) GameActivity.BG_IMAGE=R.drawable.lvl7bg;
			if (bg.equals("lvl8bg")) GameActivity.BG_IMAGE=R.drawable.lvl4bg;			
			if (bg.equals("lvl9bg")) GameActivity.BG_IMAGE=R.drawable.lvl5bg;
			if (bg.equals("lvl10bg")) GameActivity.BG_IMAGE=R.drawable.lvl6bg;
			if (bg.equals("lvl11bg")) GameActivity.BG_IMAGE=R.drawable.lvl7bg;
			if (bg.equals("lvl12bg")) GameActivity.BG_IMAGE=R.drawable.lvl4bg;
			if (bg.equals("lvl13bg")) GameActivity.BG_IMAGE=R.drawable.lvl7bg;
			if (bg.equals("lvl14bg")) GameActivity.BG_IMAGE=R.drawable.lvl4bg;			
			if (bg.equals("lvl15bg")) GameActivity.BG_IMAGE=R.drawable.lvl5bg;
			if (bg.equals("lvl16bg")) GameActivity.BG_IMAGE=R.drawable.lvl6bg;
			if (bg.equals("lvl17bg")) GameActivity.BG_IMAGE=R.drawable.lvl7bg;
			if (bg.equals("lvl18bg")) GameActivity.BG_IMAGE=R.drawable.lvl4bg;
			if (bg.equals("lvl19bg")) GameActivity.BG_IMAGE=R.drawable.lvl7bg;
			if (bg.equals("lvl20bg")) GameActivity.BG_IMAGE=R.drawable.lvl4bg;
		}
	}
}
