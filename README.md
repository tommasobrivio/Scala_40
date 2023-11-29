# REPOSITORY scala_40 

FUNZIONAMENTO GENERALE: 
•	pagina Campo invia richiesta TCP di giocare al server -> se tutti i giocatori sono connessi si gioca

•	server invia ai client le 13 carte da tenere "in mano" ai giocatori e il client le visualizza

•	ogni turno il giocatore fa le sue azioni e il server le gestisce


OBIETTIVO DEL GIOCO: 
arrivare a non avere carte in mano, giocandole sul campo

REGOLE:
•	All’inizio di ogni turno, il giocatore può pescare una carta dal mazzo o prendere la prima carta della pila degli scarti.
Se non ha carte sul proprio campo, la carta dalla pila degli scarti la può prendere solo se è in grado di poter giocare almeno 40 nello stesso turno.
•	Mentre un giocatore non ha carte in mano, per iniziare a giocare deve selezionare un certo numero di carte la cui somma deve essere almeno 40. 
Criteri per giocare carte:
-	si devono formare combinazioni da almeno 3 carte, possono essere scale ordinate (carte dello stesso seme ma di numero diverso) o carte dello stesso valore ma di seme diverso
-	i giocatori con almeno 40 punti giocati possono attaccare delle loro carte della mano ad altre combinazioni giocate dall’ avversario, purché le combinazioni finali rispettino gli stessi criteri elencati prima 
-	alla fine di ogni turno il giocatore deve scartare una carta dalla sua mano, purché non si attacchi a nessuna combinazione presente sul campo.

