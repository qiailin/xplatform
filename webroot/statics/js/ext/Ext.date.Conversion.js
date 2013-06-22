Ext.override(Date, {
			toUTC : function() {
				// Convert the date to the UTC date
				return this.add(Date.MINUTE, -840);
			}
		});