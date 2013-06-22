/**
 * @class Ext.data.SimpleJsonReader
 * @extends Ext.data.DataReader
 * Data reader class to create an Array of Ext.data.Record objects from a JSON response
 * based on mappings in a provided Ext.data.Record constructor.<br>
 * <p>
 * Example code:
 * <pre><code>
var Employee = Ext.data.Record.create([
    {name: 'name', mapping: 'name'},     // "mapping" property not needed if it's the same as "name"
    {name: 'occupation'}                 // This field will use "occupation" as the mapping.
]);
var myReader = new Ext.data.SimpleJsonReader({
	id: 'name'
}, Employee);

var store = new Ext.data.Store({
        url: 'topics-browse-remote.php',
        reader: myReader,
        remoteSort: true
});
</code></pre>
 * <p>
 * This would consume a JSON file like this:
 * <pre><code>
[
    { 'id': 1, 'name': 'Bill', occupation: 'Gardener' },
    { 'id': 2, 'name': 'Ben', occupation: 'Horticulturalist' }
]
or like this:
{values:
[
    { 'id': 1, 'name': 'Bill', occupation: 'Gardener' },
    { 'id': 2, 'name': 'Ben', occupation: 'Horticulturalist' }
]
,total: 10
}
</code></pre>
 */
Ext.data.SimpleJsonReader = function(meta, recordType){
    meta = meta || {};
    meta.totalProperty = meta.totalProperty || 'total';
    meta.root = meta.root || 'values';
    Ext.data.SimpleJsonReader.superclass.constructor.call(this, meta, recordType || meta.fields);
};
Ext.extend(Ext.data.SimpleJsonReader, Ext.data.JsonReader, {
    
    /**
     * Create a data block containing Ext.data.Records from a JSON object.
     * @param {Object} o An object which contains an Array of row objects.
     * @return {Object} data A data block which is used by an Ext.data.Store object as
     * a cache of Ext.data.Records.
     */
    readRecords : function(o){
    	var results = o, s = this.meta;
    	if( (typeof o.length != 'undefined') ) {
    		results = {values : o, total: o.length};
    	} else if( (typeof o.values == 'undefined') ) {
    		results = {values : [], total: 0};
    	}
		  if( s.blanks ) {
	    	for(var i = 0, len = s.blanks - results.values.length; i < len; i++){
	    		results.values[results.values.length] = {};
	    	}
	    }

	    return Ext.data.SimpleJsonReader.superclass.readRecords.call(this, results);
    }
});