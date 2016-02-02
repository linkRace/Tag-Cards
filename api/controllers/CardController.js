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
		console.log("english", english, "japanese",japanese, "tags",tags, "kanji", kanji);
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
				console.log("found card",c);
				res.json(c);
			} else {
				res.badRequest();
			}
		});
	}
};
