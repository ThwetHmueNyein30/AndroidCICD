package com.thn.livecodingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.Stack

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("THN", "onCreate: ${findMin()}")
    }

    fun findMin(): Int {
        val nums = intArrayOf(4,5,6,7,0,1,2)
        var l = 0
        var r = nums.lastIndex
        var res = nums[0]

        while (l <= r) {
            var mid = l + (r - l) / 2
            Log.d("THN", "findMin: $mid")
            if (nums[l] < nums[r]) {
                res = minOf(res, nums[l])
                break
            }

            val m = l + (r - l) / 2
            res = minOf(res, nums[m])
            if (nums[m] >= nums[l])
                l = mid + 1
            else
                r = m - 1
        }

        return res
    }

    fun isValid(): Boolean {
        val s = "([{}"
        if (s.length % 2 == 1) return false

        val map = hashMapOf('(' to ')', '[' to ']', '{' to '}')
        val stack = Stack<Char>()

        for (c in s) {
            if (map.containsKey(c)) {
                Log.d("THN", "isValid: if ${map[c]}")
                stack.push(c)
                Log.d("THN", "isValid: stack $stack")
            }
            else if (stack.isEmpty() || map[stack.pop()] != c) {
                Log.d("THN", "isValid: else if $stack")
                return false
            }
        }
        Log.d("THN", "isValid: after loop $stack")
        return stack.isEmpty()
    }

    private fun containsDuplicate(nums: IntArray): Boolean {
        val hashSet = HashSet<Int>(nums.size)
        for (element in nums) {
            hashSet.add(element)
        }
        return hashSet.size < nums.size
    }

    private fun isAnagram(): Boolean {
        val s = "aaccc"
        val t = "ccaaa"
        if (s.length != t.length) return false
        val firstHashSet = HashMap<Char, Int>()
        val secondHashSet = HashMap<Char, Int>()
        for (element in s) {
            firstHashSet[element] = firstHashSet.getOrDefault(element, 0) + 1
        }
        for (element in t) {
            secondHashSet[element] = secondHashSet.getOrDefault(element, 0) + 1
        }
         return firstHashSet == secondHashSet

        /*if(s.length != t.length) return false

      val firstArray = s.toCharArray().sortedArray()
      val secondArray = t.toCharArray().sortedArray()
      val firstString = String(firstArray)
      val secondString = String(secondArray)

      return firstString == secondString*/
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        for (i in 0..nums.size) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target) return intArrayOf(i, j)
            }
        }
        return intArrayOf(0, 0)

    }

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val res: HashMap<String, MutableList<String>> = hashMapOf()

        for (s in strs){
            val count = IntArray(26)

            for (c in s){
                val index = c - 'a'
                count[index] += 1
            }

            res[count.joinToString()] = res.getOrDefault(count.joinToString(), mutableListOf()).also { it.add(s) }
        }

        return res.values.toList()
    }


}



