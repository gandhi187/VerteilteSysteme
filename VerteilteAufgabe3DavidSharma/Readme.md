Thema: Dynamische (rekursive) Erzeugung von Threads im Programm.
Schreiben Sie ein Multithread-Programm, das bei einer Eingabe zweier natürlicher Zahlen n und k (dabei stets n >= k) den sogenannten Binomialkoeffizienten „n über k“ berechnet. Dieser Wert sagt aus, wie viele k-elementige Teilmengen eine Menge von
n Elementen hat. Oder wie viele verschiedene Möglichkeiten es gibt, aus einer Urne mit n numerierten Kugeln (also Kugeln mit Nummern 1 bis n) k Kugeln zu ziehen (dies ohne Zurücklegen und ohne Beachtung der Reihenfolge des Ziehens).

Ihr Programm sollte sich auf den Eingabebereich 0 < n < 20 beschränken.
Das zu schreibende Programm soll den Binomialkoeffizienten parallel (nebenläufig) berechnen nach der Divide-and-Conquer-Strategie, in der jede (Teil-) Aufgabe durch Herunterbrechen in kleinere Aufgaben handhabbar gemacht wird. Das bedeutet, dass ein gestarteter Thread seine Berechnung i.a. so ausführt, dass er dynamisch weitere Threads erzeugt und deren Berechnungen dann zu seinem Ergebnis zusammenfügt.
Diese Anforderung lässt sich mit nur einer Threadklasse mit Hilfe von Rekursion erfüllen.
Die Abnahme des Programms erfolgt nach Absprache mit mir.
