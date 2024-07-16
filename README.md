- http://localhost:9200/products/_search

```json
{
  "query": {
    "wildcard": {
      "name": "r*"
    }
  }
}
```
```json
{
  "query": {
    "match_all": {
      
    }
  }
}
```
```json
{
  "query": {
    "match": {
      
    }
  }
}
```