import java.util.Scanner;

public class FourPower {
    public static final char PIPE='|';
    public static final char POINT='.';
    public static final char PULLS='-';
    public static final int FOUR = 4;
    public static final int COLUMN_NUMBER = 7;
    public static final int LINES_NUMBER = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //The vertical game board:
        //required alignment :
        int N = FOUR;
        //columns and lines :
        int columnNumber = COLUMN_NUMBER;
        int linesNumber = LINES_NUMBER;
        //table of the board ('.' = empty space  / 'R' = Read   /  'J'  = Yellow )
        char[][] tray = new char[columnNumber][linesNumber];
        //fill in the blanks, yes it's paradoxical !
        for(int x = 0 ; x < columnNumber ; x++)
            for(int y = 0 ; y < linesNumber ; y++)
                tray[x][y] = POINT;

        int winner = 0;

        //game loop, stops in case of victory of J1 or J2 or if the board is full with equality
        for(int i = 1 ; i <= columnNumber*linesNumber ; i++){

            //display of the tray:
            System.out.println("Turn " + i + ", Tray Status:");

            for(int loop = 0 ; loop < columnNumber+2+2*columnNumber ; loop++)System.out.print(PULLS);
            System.out.println();

            for(int y = 0 ; y < linesNumber ; y++){
                System.out.print(PIPE);
                for(int x = 0 ; x < columnNumber ; x++){
                    System.out.print(" " + tray[x][y] + " ");
                }
                System.out.print(PIPE);
                System.out.println();
            }

            for(int loop = 0 ; loop < columnNumber+2+2*columnNumber ; loop++)System.out.print(PULLS);
            System.out.println();

            //cursor placement
            System.out.println("Player's turn " + (i%2==1 ? 'R' : 'J') );
            System.out.println("Enter the number of the column between 1 and " + columnNumber + " ...");
            boolean placement = false;
            int column = -1;
            while(!placement){
                column = -1;
                String line = scanner.nextLine();
                //check that the line is an integer between 1 and C:
                try{
                    column = Integer.valueOf(line);

                    if(column >= 1 && column <= columnNumber){
                        if(tray[column - 1][0] != POINT){
                            System.out.println("Column full, repeat");
                        } else {
                            placement = true;
                        }
                    } else {
                        System.out.println("Incorrect number, repeat");
                    }

                }catch(Exception e){System.out.println("Incorrect number, repeat");}

            }
            //cursor placement:
            int rang = linesNumber-1;
            while(tray[column - 1][rang] != POINT){
                rang--;
            }
            tray[column - 1][rang] = (i%2==1 ? 'R' : 'J');



            //Victory detection:

            //symbol en cours:
            char symbol = (i%2==1 ? 'R' : 'J');
            //maximum number of aligned:
            int max = 0;
            int x; int y;
            int sum;

            //  diagonal HG-BD
            x = column-1; y = rang; sum=-1;
            while(y >= 0 && x >= 0 && tray[x][y] == symbol){ y--; x--; sum++;}
            x = column-1; y = rang;
            while(y < linesNumber && x < columnNumber && tray[x][y] == symbol){ y++; x++; sum++;}
            if(sum > max) max= sum;

            //  diagonal HD-BG
            x = column-1; y = rang; sum=-1;
            while(y >= 0 && x < columnNumber && tray[x][y] == symbol){ y--; x++; sum++;}
            x = column-1; y = rang;
            while(y < linesNumber && x >= 0 && tray[x][y] == symbol){ y++; x--; sum++;}
            if(sum > max) max= sum;

            //-->  vertical:
            x = column-1; y = rang; sum=-1;
            while(y >= 0 && tray[x][y] == symbol){ y--; sum++;}
            y = rang;
            while(y < linesNumber && tray[x][y] == symbol){ y++; sum++;}
            if(sum > max) max= sum;

            //--> horizontal:
            x = column-1; y = rang; sum=-1;
            while(x >= 0 && tray[x][y] == symbol){ x--; sum++;}
            x = column-1;
            while(x < columnNumber && tray[x][y] == symbol){ x++; sum++;}
            if(sum > max) max= sum;


            if(max >= N){
                winner = (i%2==1 ? 1 : 2);
                i = columnNumber*linesNumber+1;
            }
            System.out.println("********************************");
        }


        //display of results:
        // if winner == 0 it means that the whole board is filled without winner, so there is a tie
        System.out.println();
        System.out.println("*********************");
        System.out.println("****END OF PART****");
        System.out.println("*********************");
        if(winner == 0)
            System.out.println("*******EQUALITY*******");
        if(winner == 1)
            System.out.println("****Partie gagnée par le camp : rouge****");
        if(winner == 2)
            System.out.println("****Partie gagnée par le camp : jaune****");
        System.out.println("*********************");


        for(int loop = 0 ; loop < columnNumber+2+2*columnNumber ; loop++)System.out.print(PULLS);
        System.out.println();

        for(int y = 0 ; y < linesNumber ; y++){
            System.out.print(PIPE);
            for(int x = 0 ; x < columnNumber ; x++){
                System.out.print(" " + tray[x][y] + " ");
            }
            System.out.print(PIPE);
            System.out.println();
        }

        for(int loop = 0 ; loop < columnNumber+2+2*columnNumber ; loop++)System.out.print(PULLS);
        System.out.println();
    }
}
