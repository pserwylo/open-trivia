package com.serwylo.trivia.questions.factories

/**
 */
class LineParser {

	private List<Header> headers

	private List<String> parts

	private Boolean isValid = false

	private Map<String,String> values

	LineParser( List<Header> headers, String line ) {
		this.headers = headers
		splitLine( line )
	}

	public Boolean getIsValid() {
		return isValid
	}

	public Map<String,String> getValues() {
		return values
	}

	/**
	 * Currently, this splits the line based on a comma.
	 * In the future, it will use a CSV library.
	 * @param line
	 */
	private void splitLine( String line ) {
		parts = line.split( '\t' )
	}

	/**
	 * Returns the (trimmed) value at index, from the data line we split earlier.
	 * Will return null if the value doesn't exist or if it is a zero length string.
	 * @param index
	 * @return
	 */
	private String getValueAt( Integer index ) {

		String value = null

		if ( parts.size() > index ) {

			value = parts[ index ]
			value.trim()

			if ( value?.size() == 0 ) {
				value = null
			}

		}

		return value
	}

	/**
	 * Looks through a single line from a data (CSV) file and turns it into a Map.
	 * This map will have appropriate keys, which match the headers of the data file.
	 * If a header is optional and is not provided on the line, it is stored with a null value.
	 * If a header is required and is not provided, we will not return a Map.
	 * @return
	 */
	Boolean parseLine() {

		values = [:]
		isValid = true

		for ( Integer i = 0; i < headers.size(); i ++ ) {

			Header header = headers[ i ]
			if ( parts.size() > i ) {

				String value = getValueAt( i )
				if ( value == null && header.isRequired ) {

						isValid = false
						break

				} else {

					values[ header.name ] = value

				}

			} else if ( header.isRequired ) {
				isValid = false
				break
			}

		}

		if ( !isValid ) {
			values = null
		}

		return isValid

	}

}
