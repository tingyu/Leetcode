/**
Implement strStr() 

Implement strStr().

Returns a pointer to the first occurrence of needle in haystack, or null if needle is not part of haystack.
*/

/*
First, need to understand the problem correctly, the pointer simply means a sub string.
Second, make sure the loop does not exceed the boundaries of two strings.
*/

public String strStr(String haystack, String needle) {
	int needleLen = needle.length();
	int haystackLen = haystack.length();

	if(needleLen == haystackLen && needleLen == 0){//why needleLen == haystackLen
		return "";
	}

	if(needleLen == 0) return haystack;

	for(int i = 0; i < haystackLen; i++){
		//make sure in boundary of needle
		if(haystackLen - i + 1 < needleLen){
			return null;
		}

		int k = i;
		int j = 0;

		while(j < needleLen && k < haystackLen && needle.charAt(j) == haystack.charAt(k)){
			j++;
			k++;
			if(j == needleLen)
				return haystack.substring(i);
		}
	}
	return null;
}