# react-native-mocoit-pay-u-money

## Getting started

`$ npm install react-native-mocoit-pay-u-money --save`

### Mostly automatic installation

`$ react-native link react-native-mocoit-pay-u-money`

## Usage

```javascript
import MocoitPayUMoney from "react-native-mocoit-pay-u-money";

// TODO:
MocoitPayUMoney;

MocoitPayUMoney.pay(options)
  .then((d) => {
    console.log(d); // WIll get a Success response with verification hash
  })
  .catch((e) => {
    console.log(e); //In case of failture
  });
```
