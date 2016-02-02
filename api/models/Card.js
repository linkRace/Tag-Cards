/**
* Bracket.js
*
* @description :: TODO: You might write a short summary of how this model works and what it represents here.
* @docs        :: http://sailsjs.org/#!documentation/models
*/

var Card = {
  schema: true
, adapter: 'mysql'
, tableName: 'card'
, attributes: {
    // english storage
    english: {
      type: 'string'
    , columnName: 'english'
    },
    // latin letters, japanese word
    engrish: {
      type: 'string'
    , columnName: 'engrish'
    },
    // hiragana storage
    japanese: {
      type: 'string'
    , columnName: 'japanese'
    },
    // kanji form
    kanji: {
      type: 'string'
    , columnName: 'kanji'
    },
    // comma seperated tags like lesson1,u-verb,current
    tags: {
      type: 'string'
    , columnName: 'tags'
    }
  }

}

module.exports = Card;
