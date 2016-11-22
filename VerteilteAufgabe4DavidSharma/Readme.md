Schreiben Sie ein verteiltes Programm, das eine Online-Meinungsumfrage zu einem vorgegebenen Thema umsetzt.
Auf dem serverseitigen Teil wird dabei der aktuelle Stand der Meinungsumfrage (Abstimmmöglichkeiten: Zustimmung=ja, Ablehnung=nein, Enthaltung=sonstiges) verwaltet. Der momentane Abstimmungsstand soll dabei persistent in einer Datei abgespeichert werden. Dieser Serverteil soll als konkurrenter Server umgesetzt werden.
Es soll 2 Klientenprogramme geben.
Unter Nutzung des ersten Klientenprogramms können beliebig viele Nutzer ihre Meinung zu diesem Thema kundtun (Abstimmung mit ja, nein oder Enthaltung). Mit dem zweiten Klientenprogramm kann der aktuelle Stand der Abstimmung vom Server erfragen werden.
Diese Aufgabe 4 ist unter Nutzung des verteilten Programmieransatzes Socket-Programmierung zu bearbeiten.
Die ablaufenden Klientenprogrammaufrufe (abstimmwillige Teilnehmer oder Fragende nach dem Umfragestand jeweils in beliebiger Zahl von verschiedenen Rechnern) erteilen ihre Aufträge (Meinungsäußerungen, Standanfrage) an den Server über das Netzwerk.
Da viele solche Meinungsäußerungen auch gleichzeitig über das Netzwerk beim Server eintreffen können, überlegen Sie, ob die Bearbeitung dieser Anforderungen durch den Server synchronisiert werden muss.
Die Abnahme des Programms erfolgt nach Absprache mit mir, zur Vorführung des lauffähigen Programms müssen mehrere Rechner eingesetzt werden.
