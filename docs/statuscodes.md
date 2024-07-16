# HTTP Statuscodes

## 1xx Informational
- **100 Continue**: Der Client kann mit seiner Anfrage fortfahren.
- **101 Switching Protocols**: Der Server wechselt das Protokoll gemäß der Anfrage des Clients.
- **102 Processing (WebDAV)**: Der Server hat die Anfrage erhalten und bearbeitet sie.

## 2xx Success
- **200 OK**: Die Anfrage war erfolgreich.
- **201 Created**: Die Anfrage wurde erfolgreich ausgeführt und eine neue Ressource wurde erstellt.
- **202 Accepted**: Die Anfrage wurde akzeptiert, aber noch nicht abgeschlossen.
- **203 Non-Authoritative Information**: Die Anfrage war erfolgreich, aber die zurückgegebenen Metainformationen stammen von einer anderen Quelle.
- **204 No Content**: Die Anfrage war erfolgreich, aber es gibt keine Rückmeldung.
- **205 Reset Content**: Die Anfrage war erfolgreich und der Client sollte das Formular zurücksetzen.
- **206 Partial Content**: Der Server liefert nur einen Teil der Ressource (durch Range-Header spezifiziert).
- **207 Multi-Status (WebDAV)**: Der Body enthält Informationen über mehrere Ressourcen.

## 3xx Redirection
- **300 Multiple Choices**: Mehrere Optionen für die angeforderte Ressource.
- **301 Moved Permanently**: Die Ressource wurde dauerhaft verschoben.
- **302 Found**: Die Ressource wurde vorübergehend verschoben.
- **303 See Other**: Die Antwort befindet sich unter einer anderen URI.
- **304 Not Modified**: Die angeforderte Ressource wurde nicht verändert.
- **305 Use Proxy**: Die angeforderte Ressource muss über einen Proxy abgerufen werden.
- **307 Temporary Redirect**: Die Ressource wurde vorübergehend verschoben.
- **308 Permanent Redirect**: Die Ressource wurde dauerhaft verschoben (neuer Standard).

## 4xx Client Error
- **400 Bad Request**: Die Anfrage war fehlerhaft.
- **401 Unauthorized**: Authentifizierung erforderlich.
- **402 Payment Required**: Zahlung erforderlich (reserviert für zukünftige Verwendung).
- **403 Forbidden**: Zugriff verweigert.
- **404 Not Found**: Die angeforderte Ressource wurde nicht gefunden.
- **405 Method Not Allowed**: Die verwendete HTTP-Methode ist für diese Ressource nicht erlaubt.
- **406 Not Acceptable**: Die angeforderte Ressource ist nicht im gewünschten Format verfügbar.
- **407 Proxy Authentication Required**: Authentifizierung am Proxy erforderlich.
- **408 Request Timeout**: Die Anfrage hat zu lange gedauert.
- **409 Conflict**: Konflikt bei der Anfrage.
- **410 Gone**: Die Ressource ist dauerhaft entfernt.
- **411 Length Required**: Die Länge der Anfrage muss angegeben werden.
- **412 Precondition Failed**: Eine Vorbedingung der Anfrage wurde nicht erfüllt.
- **413 Payload Too Large**: Die Anfrage ist zu groß.
- **414 URI Too Long**: Die angeforderte URI ist zu lang.
- **415 Unsupported Media Type**: Das Format der Anfrage wird nicht unterstützt.
- **416 Range Not Satisfiable**: Der angeforderte Bereich kann nicht bereitgestellt werden.
- **417 Expectation Failed**: Eine Erwartung der Anfrage wurde nicht erfüllt.
- **418 I'm a teapot**: April-Scherz-Statuscode von IETF.
- **421 Misdirected Request**: Die Anfrage wurde an einen Server geschickt, der nicht in der Lage ist, eine Antwort zu geben.
- **422 Unprocessable Entity (WebDAV)**: Die Anfrage war fehlerhaft (semantisch).
- **423 Locked (WebDAV)**: Die Ressource ist gesperrt.
- **424 Failed Dependency (WebDAV)**: Die Anfrage scheiterte aufgrund eines fehlgeschlagenen Abhängigkeits-Checks.
- **425 Too Early**: Der Server ist nicht bereit, die Anfrage zu verarbeiten.
- **426 Upgrade Required**: Das Protokoll muss gewechselt werden.
- **428 Precondition Required**: Die Anfrage muss eine Bedingung erfüllen.
- **429 Too Many Requests**: Zu viele Anfragen in kurzer Zeit.
- **431 Request Header Fields Too Large**: Die Headerfelder der Anfrage sind zu groß.
- **451 Unavailable For Legal Reasons**: Die Anfrage ist aus rechtlichen Gründen nicht verfügbar.

## 5xx Server Error
- **500 Internal Server Error**: Allgemeiner Serverfehler.
- **501 Not Implemented**: Die Funktionalität wird vom Server nicht unterstützt.
- **502 Bad Gateway**: Ungültige Antwort vom Gateway.
- **503 Service Unavailable**: Der Service ist vorübergehend nicht verfügbar.
- **504 Gateway Timeout**: Zeitüberschreitung des Gateways.
- **505 HTTP Version Not Supported**: Die HTTP-Version wird nicht unterstützt.
- **506 Variant Also Negotiates**: Die angeforderte Variante verursacht ein Negationsproblem.
- **507 Insufficient Storage (WebDAV)**: Der Server hat nicht genug Speicherplatz.
- **508 Loop Detected (WebDAV)**: Endlosschleife entdeckt.
- **510 Not Extended**: Weitere Erweiterungen der Anfrage sind erforderlich.
- **511 Network Authentication Required**: Netzwerk-Authentifizierung erforderlich.
