beanie
======
* AreaChartSample, BinaryWebSocketServer en JavaFXBinaryWsClient zijn 'inspiratie'projecten.
* brewery is het domein model
* movieplex7 is de solution van het hands-on lab van Oracle
* workshop-ee7-java8 is een licht aangepast versie
* BreweryApp bevat de AngularJS broncode om de front-end applicatie te maken

Het project workshop-ee7-java8 bevast de sources die de deelnemer krijgt. Het domein model (brewery) komt mee als jar.

# API
## Manually performing actions via REST interface

Add ingredient
POST /resources/ingredient/{name}/{value}/{unit}
```json
{ "ingredient":"yeast", "volume": { "value":"300", "unit":"liter" } }
```
Change temperature
POST /resources/temperature/{value}/{unit}
```json
{ "temperature": { "value":"65", "unit":"celsius" } }
```

Wait for period of time
POST /resources/wait/{duration}
```json
{ "duration":"PT30M" }
```

Explanation of PT30M (30 minutes): http://en.wikipedia.org/wiki/ISO_8601

TODO: add responses
## Recipe JMS 

Recipe
POST /resources/recipe
```json
{  
   "name":"KoenBier",
   "steps":[  
      {  
         "type":"addIngredient",
         "ingredient":"naam",
         "volume":{  
            "value":"300",
            "unit":"liter"
         }
      },
      {  
         "type":"changeTemperature",
         "temperature":{  
            "value":"65",
            "scale":"celsius"
         }
      },
      {  
         "type":"stableTemperature",
         "duration":"PT30M"
      }
   ]
}
```
duration -> Duration.of(30, ChronoUnit.MINUTES).toString();

##Monitoring through WebSockets

```json
{"event":"recipe completed"}

{"event":"waiting completed"}

{  
   "event":"temperature changing",
   "kettle":{        
      "scale":"CELSIUS",
      "value":0
   },
   "goal":{  
      "scale":"CELSIUS",
      "value":65
   }
}

{"event":"temperature reading","temperature":{"scale":"CELSIUS","value":65}}

{"event":"waiting","duration":"PT30M"}

{  
   "event":"temperature reached goal",
   "temperature":{  
      "scale":"CELSIUS",
      "value":65
   }
}

{  
   "event":"ingredient added",
   "ingredient":{  
      "name":"Hop",
      "volume":{  
         "value":300,
         "unit":"LITER"
      }
   }
}
```




