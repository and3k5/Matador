/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matador;

import java.util.Random;

/**
 *
 * @author and3k5
 */
public class Luckcard {
    
    public static String Text;
    static void TryCard(Player P)
    {
        int[] Luckcards = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33};
        Text = "";
        int length = Luckcards.length;
        int randomcard;
        randomcard = new Random().nextInt(length-1);
        switch (Luckcards[randomcard]){
            case 1:
                Text = "Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer 'START', indkasserer De ikke kr. 4000.";
                P.InPrison = true;
                P.Position = 10;
                break;
            case 2:
                Text = "De har købt 4 nye dæk til Deres vogn. Betal kr. 1000.";
                P.ChangeMoney(-1000);
                break;
            case 3:
                Text = "Ryk frem til 'START'.";
                P.Position = 0;
                Game.fields.get(P.Position).Lands(P);
                break;
            case 4:
                Text = "Deres præmieobligation er udtrukket. De modtager kr. 1000 af banken.";
                P.ChangeMoney(1000);
                break;
            case 5:
                Text = "Betal kr. 3000 for reparation af Deres vogn.";
                P.ChangeMoney(-3000);
                break;
            case 6:
                Text = "Grundet dyrtiden har De fået gageforhøjelse. Modtag kr. 1000.";
                P.ChangeMoney(1000);
                break;
            case 7:
                Text = "De har været en tur i udlandet og haft for mange cigaretter med hjem. Betal told kr. 200.";
                P.ChangeMoney(-200);
                break;
            case 8:
                Text = "Betal for vognvask og smøring kr. 300.";
                P.ChangeMoney(-300);
                break;
            case 9:
                Text = "Betal kr. 200 for levering af 2 kasser øl.";
                P.ChangeMoney(-200);
                break;
            case 10:
                Text = "Ryk tre felter tilbage";
                P.ChangePosition(-3);
                Game.fields.get(P.Position).Lands(P);
                break;
            case 11:
                Text = "Ryk tre felter frem";
                P.ChangePosition(3);
                Game.fields.get(P.Position).Lands(P);
                break;
            case 12:
                Text = "Modtag udbytte af Deres aktier - kr. 1.000.";
                P.ChangeMoney(1000);
                break;
            case 13:
                Text = "De har vundet i klasselotteriet. Modtag kr. 500.";
                P.ChangeMoney(500);
                break;
            case 14:
                Text = "I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det.";
                P.GetOutCard = P.GetOutCard+1;
                break;
            case 15:
                Text = "Ryk frem til Vimmelskaftet. Hvis de passerer 'START', indkasser da kr. 4.000.";
                if (P.Position>32) {
                    // User has to pass start
                    Game.fields.get(0).Passed(P);
                }
                P.Position=32;
                break;
            case 16:
                Text = "Værdien af egen avl fra nyttehaven udgør kr. 200, som De modtager af banken.";
                P.ChangeMoney(200);
                break;
            case 17:
                Text = "De har modtaget Deres tandlægeregning. Betal kr. 2.000.";
                P.ChangeMoney(-2000);
                break;
            case 18:
                Text = "Tag med den nærmeste færge. Flyt brikken frem, og hvis De passerer 'START', indkasser da kr. 4.000.";
                if (P.Position>35) {
                    // User has to pass start
                    Game.fields.get(0).Passed(P);
                    P.Position=5;
                }else if (P.Position>25) {
                    P.Position=35;
                }else if (P.Position>15) {
                    P.Position=25;
                }else if (P.Position>5) {
                    P.Position=15;
                }
                break;
            case 19:
                Text = "Kommunen har eftergivet et kvartals skat. Hæv i banken kr. 3.000.";
                P.ChangeMoney(3000);
                break;
            case 20:
                Text = "De har fået en parkeringsbøde. Betal kr. 200 i bøde.";
                P.ChangeMoney(-200);
                break;
            case 21:
                Text = "Betal Deres bilforsikring - kr. 1.000.";
                P.ChangeMoney(-1000);
                break;
            case 22:
                Text = "Ryk frem til Strandvejen. Hvis De passerer 'START', indkasser da kr. 4.000.";
                if (P.Position>19) {
                    // User has to pass start
                    Game.fields.get(0).Passed(P);
                }
                P.Position=19;
                break;
            case 23:
                Text = "Tag ind på Rådhuspladsen.";
                P.Position=39;
                break;
            case 24:
                Text = "Det er Deres fødselsdag. Modtag af hver medspiller kr. 200.";
                for (Player OtherP: Game.players)
                {
                    if (Game.players.indexOf(OtherP) != Game.players.indexOf(P))
                    {
                        OtherP.ChangeMoney(-200);
                        P.ChangeMoney(200);
                    }
                }
                break;
            case 25:
                Text = "De har lagt penge ud til et sammenskudsgilde. Mærkværdigvis betaler alle straks. Modtag fra hver medspiller kr. 500.";
                for (Player OtherP: Game.players)
                {
                    if (Game.players.indexOf(OtherP) != Game.players.indexOf(P))
                    {
                        OtherP.ChangeMoney(-500);
                        P.ChangeMoney(500);
                    }
                }
                break;
            case 26:
                Text = "De har kørt frem for 'Fuldt stop'. Betal kr. 1.000 i bøde.";
                P.ChangeMoney(-1000);
                break;
            case 27:
                Text = "Ryk frem til Frederiksberg Allé. Hvis De passerer 'START', indkasser da kr. 4.000.";
                if (P.Position>11) {
                    // User has to pass start
                    Game.fields.get(0).Passed(P);
                }
                P.Position=11;
                break;
            case 28:
                Text = "Ryk frem til Grønningen. Hvis De passerer 'START', indkasser da kr. 4.000.";
                if (P.Position>24) {
                    // User has to pass start
                    Game.fields.get(0).Passed(P);
                }
                P.Position=24;
                break;
            case 29:
                Text = "De skal holde familiefest og får et tilskud fra hver medspiller på kr. 500.";
                for (Player OtherP: Game.players)
                {
                    if (Game.players.indexOf(OtherP) != Game.players.indexOf(P))
                    {
                        OtherP.ChangeMoney(-500);
                        P.ChangeMoney(500);
                    }
                }
                break;
            case 30:
                Text = "Tag med Mols-Linien. Flyt brikken frem, og hvis De passerer 'START', indkasser da kr. 4.000.";
                if (P.Position>15) {
                    // User has to pass start
                    Game.fields.get(0).Passed(P);
                }
                P.Position=15;
                break;
            case 31:
                Text = "De har solgt nogle gamle møbler på auktion. Modtag kr. 1.000 af banken.";
                P.ChangeMoney(1000);
                break;
            case 32:
                Text = "De havde en række med elleve rigtige i tipning. Modtag kr. 1.000.";
                P.ChangeMoney(1000);
                break;
            case 33:
                Text = "Ryk brikken frem til det nærmeste rederi og betal ejeren to gange den leje, han ellers er berettiget til. Hvis selskabet ikke ejes af nogen, kan De købe det af banken.";
                if (P.Position>35) {
                    P.Position=5;
                    SLDouble(P);
                    Game.fields.get(P.Position).Lands(P);
                }else if (P.Position>25) {
                    P.Position=35;
                    SLDouble(P);
                    Game.fields.get(P.Position).Lands(P);
                }else if (P.Position>15) {
                    P.Position=25;
                    SLDouble(P);
                    Game.fields.get(P.Position).Lands(P);
                }else if (P.Position>5) {
                    P.Position=15;
                    SLDouble(P);
                    Game.fields.get(P.Position).Lands(P);
                }
                break;
        }
    }
    private static void SLDouble(Player P)
    {
        ShippingLines SL = (ShippingLines)Game.fields.get(P.Position);
        int Owner = SL.Owner;
        if (Owner != Game.players.indexOf(P))
        {
            if ((Game.players.get(Owner)).InPrison == false)
            {
                
                int cnt = SL.CountShippingLines();
                Player OPlayer = Game.players.get(Owner);
                if (cnt == 1)
                {
                    int Pay;
                    Pay = -500;
                    P.ChangeMoney(Pay);
                    OPlayer.ChangeMoney(-Pay);
                }
                else if (cnt == 2)
                {
                    int Pay;
                    Pay = -1000;    
                    P.ChangeMoney(Pay);
                    OPlayer.ChangeMoney(-Pay);
                }
                else if (cnt == 3)
                {
                    int Pay;
                    Pay = -2000;    
                    P.ChangeMoney(Pay);
                    OPlayer.ChangeMoney(-Pay);
                }
                else if (cnt == 4)
                {
                    int Pay;
                    Pay = -4000;
                    P.ChangeMoney(Pay);
                    OPlayer.ChangeMoney(-Pay);
                }
                
            }
        }
    }
    
}
