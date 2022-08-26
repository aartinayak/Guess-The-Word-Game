package com.company;//package com.company;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;
import java.lang.Math;


/**
 *  This program is a word guessing game that is played between two players
 *  for every letter guessed correctly, a fraction of the picture corresponding to that
 *  word will be displayed.
 *  If the player makes an incorrect guess then the turn goes to the next player
 */



class Player
{
    String playerName; //names of the player playing the game taken from cmd
    int noLettersGuessed; //keeps the count of the letters correctly guessed
    char playerGuess[]; // stores the letters guessed by the user
};


class Game
{
    int wordLength; //to get the length of the word to compare
    char playerInput; //stores input given by the user per character
    //String FilepathSuperman; // takes file from the user to display the picture
    //String FilepathBatman; // takes file from the user to display the picture
    Player p1; //object for player one
    Player p2; //object for player two


    /**
     * This function serves as the game logic, where in a loop is run until wither of the player
     * guesses the word correctly
     *
     * @param     void
     *
     * @exception  IOException    when file does not exist at given path
     */


    //function to run the game logic
    public void gameRun()
    {
        Scanner sc = new Scanner(System.in);
//        player p1 = new player();
//        player p2 = new player();

        pictureprint picturedraw = new pictureprint();
        int turn = 1; //to keep track of the player
        char superman[]= ("superman").toCharArray(); //original guessing word for player one
        char batman[]= ("batman").toCharArray(); //original guessing word for player two

        p1.noLettersGuessed = 0; //keeps track of the words guessed by player one
        p2.noLettersGuessed = 0; //keeps track of the words guessed by player two
        p1.playerGuess = ("........").toCharArray();
        p2.playerGuess = ("......").toCharArray();


        while(p1.noLettersGuessed<8 && p2.noLettersGuessed<6)
        {
            if(turn==1)
            {
                System.out.println("******************************" +p1.playerName+"'s turn  "
                        + "******************************");
                System.out.println();
                System.out.print( p1.playerName +"("+String.valueOf(p1.playerGuess)+") :-");
                playerInput=sc.next().charAt(0);
                System.out.println(p2.playerName + "("+String.valueOf(p2.playerGuess)+") :-");

                p1.playerGuess=wordCompare(superman,playerInput,p1.playerGuess);
                System.out.println("Original String:"+String.valueOf(superman));
                System.out.println("Players guess: "+String.valueOf(p1.playerGuess));


                if(letterCompare(superman,playerInput))
                {
                    p1.noLettersGuessed++;
                    System.out.println("You guess was correct: "+String.valueOf(p1.playerGuess));
                    picturedraw.supermanPrint(p1.noLettersGuessed);
                    superman = updateOriginalWord(superman,playerInput);
                }
                else
                {
                    turn =2;
                }


            }

            else
            {
                System.out.println("******************************" + p2.playerName+"'s turn  "
                        + "******************************");
                System.out.println();
                System.out.print(p1.playerName+ "("+String.valueOf(p1.playerGuess)+") :-");
                System.out.println();
                System.out.print(p2.playerName+ "("+String.valueOf(p2.playerGuess)+") :-");
                playerInput=sc.next().charAt(0);

                p2.playerGuess = wordCompare(batman, playerInput,p2.playerGuess);
                System.out.println("Original String:"+String.valueOf(batman));
                System.out.println("Players guess: "+String.valueOf(p2.playerGuess));
                if(letterCompare(batman, playerInput))
                {
                    p2.noLettersGuessed++;
                    System.out.println("You guess was correct:"+String.valueOf(p2.playerGuess));
                    picturedraw.batmanPrint(p2.noLettersGuessed);
                    batman=updateOriginalWord(batman,playerInput);
                }
                else
                {
                    turn=1;
                }
            }

        }

        if(p1.noLettersGuessed == 8)
        {
            System.out.println("******************************" + p1.playerName + "wins!!!  "
                    + "******************************");
        }

        if(p2.noLettersGuessed == 6)
        {
            System.out.println("******************************" +p2.playerName+"!!!  "
                    + "******************************");
        }

        sc.close();
    }



    public static char[] wordCompare(char[] original,char userInput, char[]  lettersGuessed)
    {
        int length=original.length;
        //System.out.println("Array length :- "+length);
        for(int i=0;i<length;i++)
        {
            if(userInput==original[i])
            {
                lettersGuessed[i]=original[i];
                break;
            }
        }
        return lettersGuessed;
    }



    public static boolean letterCompare(char[] original,char userInput)
    {  int length=original.length;
        int count=0;
        for(int i=0;i<length;i++)
        {
            if(userInput==original[i])
            {
                count=1;
                break;

            }
            else
                count=0;


        }
        if(count==1)
            return true;
        else
            return false;

    }

    public static char[] updateOriginalWord(char [] original,char userInput)
    {
        int length=original.length;
        for(int i=0;i<length;i++)
        {
            if(userInput==original[i])
            {
                original[i]="*".charAt(0);
                break;
            }
        }
        return original;
    }


};

//class words
//{
//    char wordArray[];
//};


class pictureprint
{
    String filepath;
    int numberOfLines;
    String FilepathSuperman; // takes file from the user to display the picture
    String FilepathBatman; // takes file from the user to display the picture


    /**
     * This function is used to print out part of the Superman image , depending
     * on the given parameter
     *
     * @param     n     used to identify what fraction of the picture should
     *                             be displayed
     *
     * @exception  IOException    when file does not exist at given path
     */

    public void supermanPrint(int n)
    {
        if (n > 8) {
            System.out.println("Enter a number between 1-8");
        }
        try {
            //Creating file objects
            this.filepath = "C:\\Users\\user\\Downloads\\supermantext.txt";
            File f = new File(this.filepath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            this.numberOfLines = lineCount(this.filepath);
            char[][] data = new char[80][this.numberOfLines];
            int i = 0;
            int j = 0;
            int range = 15 - n;
            while (i < this.numberOfLines) {
                data[i] = (br.readLine()).toCharArray();
                for (j = 0; j < 80; j++) {
                    int x = (int) Math.round(Math.random() * (range));
                    if (x < n && n != 0) {
                        System.out.print(data[i][j]);
                    } else {
                        System.out.print(".");
                    }

                }
                System.out.println();
                i++;
            }
            System.out.println();
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int lineCount(String imageFile) {
        int count=0;
        try {
            File file = new File(imageFile);
            Scanner sc = new Scanner(file);
            // count number of lines
            while(sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
            sc.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return count;
    }


    /**
     * This function is used to print out part of the Batman image , depending
     * on the given parameter
     *
     * @param     n     used to identify what fraction of the picture should
     *                             be displayed
     *
     * @exception  IOException    when file does not exist at given path
     */
    public void batmanPrint(int n) {
        if(n>6)
        {System.out.println("Enter a number between 1-6");}
        try {
            //Creating file objects
            this.filepath = "C:\\Users\\user\\Downloads\\batmantext.txt";
            File f=new File(this.filepath);
            FileReader fr= new FileReader(f);
            BufferedReader br= new BufferedReader(fr);
            int noLines=lineCount(this.filepath);

            char[][] data= new char[80][noLines];

            int i=0;
            int j=0;
            int range=11-n;
            while(i<noLines)
            {  data[i]=(br.readLine()).toCharArray();
                for(j=0;j<80;j++)
                {
                    int x=(int) Math.round(Math.random()*(range));
                    //System.out.println(x);
                    if(x<n&&n!=0)
                    {System.out.print(data[i][j]);}
                    else
                    {System.out.print(".");}

                }
                System.out.println();
                i++;
            }
            System.out.println();
            br.close();
            fr.close();

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}

public class picture {

    /**
     *  In the main function we run a while loop that keeps running until
     *  player 1 or player 2 guess' all their letters correctly and
     *  for each letter that was guessed correctly a fraction of the image
     *  is displayed on screen.
     *
     * @param args
     * @throws IOException if the files do not exist
     */

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        Player p1 = new Player();
        Player p2 = new Player();
        Game mainRun = new Game();

        pictureprint s = new pictureprint();
        pictureprint b = new pictureprint();

        s.FilepathSuperman = args[0];
        b.FilepathBatman = args[1];

        System.out.println(" args passed "+args[2]);
        p1.playerName = args[2];
        p2.playerName = args[3];
        mainRun.p1=p1;
        mainRun.p2=p2;

        mainRun.gameRun();

