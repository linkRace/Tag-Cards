/**
* Kanji.js
*
* @description :: TODO: You might write a short summary of how this model works and what it represents here.
* @docs        :: http://sailsjs.org/#!documentation/models
*/

var Kanji = {
  schema: true
, adapter: 'mysql'
, tableName: 'kanji'
, attributes: {
    // english root meaning, ex Moon
    english: {
      type: 'string'
    , columnName: 'english'
    },
    // japanese meanings, comma-separating, ex getsu,bi
    japanese: {
      type: 'string'
    , columnName: 'japanese'
    },
    // array of japanese words, comma-separated, that have the kanji in it
    examples: {
      type: 'string'
    , columnName: 'examples'
    },
    still: {
      type: 'string'
    , columnName: 'still'
    },
    // location of image with strokes
    motion: {
      type: 'string'
    , columnName: 'motion'
    },
    // comma seperated tags like lesson1,u-verb,current
    tags: {
      type: 'string'
    , columnName: 'tags'
    }
  }

}

module.exports = Kanji;
