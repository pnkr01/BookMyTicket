import { Button } from "@/Components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "@/Components/ui/dialog";
import React from "react";
import { FaGoogle } from "react-icons/fa6";

const LoginDialog = ({ open, onClose }) => {
  return (
    <Dialog open={open} onOpenChange={onClose}>
      <DialogContent className="max-w-md rounded-lg p-6 shadow-lg">
        <DialogHeader>
          <DialogTitle className="text-2xl font-semibold">
            Login to your account
          </DialogTitle>
          <DialogDescription className="text-gray-400">
            Enter your email below to login
          </DialogDescription>
        </DialogHeader>
        <button className="flex justify-center items-center gap-2 px-4 py-2 rounded-md bg-indigo-600 text-white hover:bg-indigo-500">
          <span className="text-lg">
            <FaGoogle />
          </span>
          Google
        </button>
        <div className="flex items-center justify-center text-gray-500 my-4">
          <span className="flex-1 border-t border-gray-600"></span>
          <span className="mx-4">OR CONTINUE WITH</span>
          <span className="flex-1 border-t border-gray-600"></span>
        </div>
        <form>
          <div className="flex flex-col gap-3">
            <input
              type="email"
              placeholder="Email"
              className="p-3 border-2 border-gray-300 placeholder-gray-500 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
              required
            />
            <input
              type="password"
              placeholder="Password"
              className="p-3 border-2 border-gray-300 placeholder-gray-500 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
              required
            />
          </div>
          <DialogFooter className="mt-6">
            <Button
              type="submit"
              className="w-full bg-indigo-600 text-white hover:bg-indigo-500 p-3 rounded-lg font-semibold"
            >
              Login
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
};

export default LoginDialog;
