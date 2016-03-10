/**
 * KanjiController
 *
 * @description :: Server-side logic for managing Brackets
 * @help        :: See http://links.sailsjs.org/docs/controllers
 */

module.exports = {
	insert: function(req, res) {
		var english = req.param("english"),
				japanese = req.param("japanese"),
				examples = req.param("examples"),
				still = req.param("still"),
				tags = req.param("tags");
		Kanji.create({english:english, japanese:japanese, tags:tags, still: still, examples:examples}).exec(function(err,c) {
			if (err) {
				console.error(err);
				res.serverError(err);
			} else if (c) {
				console.log("created kanji",c);
				c.motion = "kanjimotion" + c.id;
				c.save(res.ok());
			} else {
				res.badRequest();
			}
		});
	},
	findKanji: function(req, res) {
		Kanji.find({}, {select: ['id', 'japanese', 'english', 'examples', 'still', 'motion', 'tags']}).exec(function(err,c) {
			if (err) {
				console.error(err);
				res.serverError(err);
			} else if (c) {
				for (var temp, i = 0, length = c.length; i < length; ++i) {
					c[i].tags = c[i].tags.split(",");
					c[i].examples = c[i].examples.split(",");
					c[i].japanese = c[i].japanese.split(",");
				}
				res.json(c);
			} else {
				res.badRequest();
			}
		});
	}
};
