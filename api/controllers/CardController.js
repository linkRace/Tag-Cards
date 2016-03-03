/**
 * CardController
 *
 * @description :: Server-side logic for managing Brackets
 * @help        :: See http://links.sailsjs.org/docs/controllers
 */

module.exports = {
	insert: function(req, res) {
		var english = req.param("english"),
				japanese = req.param("japanese"),
				kanji = req.param("kanji"),
				tags = req.param("tags");
		if (kanji === "") {
			kanji = japanese;
		}
		Card.create({english:english, japanese:japanese, tags:tags, kanji:kanji}).exec(function(err,c) {
			if (err) {
				console.error(err);
				res.serverError(err);
			} else if (c) {
				console.log("created card",c);
				res.ok();
			} else {
				res.badRequest();
			}
		});
	},
	findCards: function(req, res) {
		Card.find({}, {select: ['id', 'japanese', 'english', 'kanji', 'tags']}).exec(function(err,c) {
			if (err) {
				console.error(err);
				res.serverError(err);
			} else if (c) {
				for (var temp, i = 0, length = c.length; i < length; ++i) {
					c[i].tags = c[i].tags.split(",");
				}
				res.json(c);
			} else {
				res.badRequest();
			}
		});
	}, clearCurrent: function(req, res) {
		Card.find({}, {select: ['id', 'japanese', 'english', 'kanji', 'tags']}).exec(function(err,c) {
			if (err) {
				console.error(err);
				res.serverError(err);
			} else if (c) {
				for (var temp, i = 0, length = c.length; i < length; ++i) {
					if (c[i].tags.match(/,current/)) {
						c[i].tags = c[i].tags.replace(/,current/,"");
						c[i].save();
					}
				}
				res.ok();
			} else {
				res.badRequest();
			}
		});
	}, updateNa: function(req, res) {
		Card.find({}, {select: ['id', 'japanese', 'english', 'kanji', 'tags']}).exec(function(err,c) {
			if (err) {
				console.error(err);
				res.serverError(err);
			} else if (c) {
				for (var temp, i = 0, length = c.length; i < length; ++i) {
					if (c[i].tags.match(/,na/)) {
						c[i].japanese = c[i].japanese.substring(0, c[i].japanese.length - 3) + "な";
						c[i].save();
					}
				}
				res.ok();
			} else {
				res.badRequest();
			}
		});
	}, kanjiConvert: function(req,res) {
		Card.find({}, {select: ['id', 'japanese', 'english', 'kanji', 'tags']}).exec(function(err,c) {
			if (err) {
				console.error(err);
				res.serverError(err);
			} else if (c) {
				for (var temp, i = 0, length = c.length; i < length; ++i) {
					if (c[i].kanji === "") {
						c[i].kanji = c[i].japanese;
						c[i].save();
					}
				}
				res.ok();
			} else {
				res.badRequest();
			}
		});
	}
};
